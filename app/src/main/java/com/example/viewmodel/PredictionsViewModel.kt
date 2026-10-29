package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.LanguagePreferences
import com.example.data.PredictionRepository
import com.example.model.AppLanguage
import com.example.model.DateGroupedPredictions
import com.example.model.LanguageStrings
import com.example.model.PredictionEntity
import com.example.model.PredictionResult
import com.example.model.VipCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

class PredictionsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repository = PredictionRepository(database.predictionDao())
    private val languagePreferences = LanguagePreferences(application)

    private val _currentLanguage = MutableStateFlow(languagePreferences.getLanguage())
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _strings = MutableStateFlow(LanguageStrings(_currentLanguage.value))
    val strings: StateFlow<LanguageStrings> = _strings.asStateFlow()

    init {
        viewModelScope.launch {
            repository.ensureSeeded()
        }
    }

    fun setLanguage(language: AppLanguage) {
        languagePreferences.setLanguage(language)
        _currentLanguage.value = language
        _strings.value = LanguageStrings(language)
    }

    // Free Tips grouped by date
    val freeTipsGrouped: StateFlow<List<DateGroupedPredictions>> =
        repository.freePredictions
            .map { list -> groupPredictionsByDate(list, ascendingDates = false) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // Active VIP predictions flows
    fun getActiveVipGrouped(category: VipCategory): StateFlow<List<DateGroupedPredictions>> {
        return repository.getActiveVipPredictions(category)
            .map { list -> groupPredictionsByDate(list, ascendingDates = true) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    // History VIP predictions flows
    fun getHistoryVipGrouped(category: VipCategory): StateFlow<List<DateGroupedPredictions>> {
        return repository.getHistoryVipPredictions(category)
            .map { list -> groupPredictionsByDate(list, ascendingDates = false) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun updatePredictionResult(id: String, result: PredictionResult) {
        viewModelScope.launch {
            repository.updateResult(id, result)
        }
    }

    fun resetDemoData() {
        viewModelScope.launch {
            repository.resetDemoData()
        }
    }

    companion object {
        fun groupPredictionsByDate(
            predictions: List<PredictionEntity>,
            ascendingDates: Boolean = true
        ): List<DateGroupedPredictions> {
            if (predictions.isEmpty()) return emptyList()

            val grouped = predictions.groupBy { it.matchDate }
            val sortedKeys = if (ascendingDates) {
                grouped.keys.sorted()
            } else {
                grouped.keys.sortedDescending()
            }

            return sortedKeys.map { dateKey ->
                val items = grouped[dateKey].orEmpty()
                val displayDate = items.firstOrNull()?.displayDate ?: dateKey
                val totalGames = items.size

                // Multiply decimal odds safely:
                val rawAccumulated = items.fold(1.0) { acc, item ->
                    val odds = if (item.odds > 0.0) item.odds else 1.0
                    acc * odds
                }
                // Round to 2 decimal places
                val accumulatedOdds = if (totalGames == 0) 1.0 else {
                    Math.round(rawAccumulated * 100.0) / 100.0
                }

                DateGroupedPredictions(
                    matchDate = dateKey,
                    displayDate = displayDate,
                    predictions = items,
                    totalGames = totalGames,
                    accumulatedOdds = accumulatedOdds
                )
            }
        }
    }
}
