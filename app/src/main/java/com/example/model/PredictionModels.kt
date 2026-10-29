package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class PredictionResult {
    PENDING,
    WON,
    LOST
}

enum class VipCategory(val storageKey: String) {
    TWO_PLUS("2_plus"),
    THREE_PLUS("3_plus"),
    FIVE_PLUS("5_plus"),
    SPECIAL("special")
}

enum class MainSection {
    FREE,
    VIP
}

@Entity(tableName = "predictions")
data class PredictionEntity(
    @PrimaryKey
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val league: String,
    val market: String,
    val odds: Double,
    val matchDate: String, // e.g. "2026-10-12" (ISO date for chronological sorting)
    val displayDate: String, // e.g. "Monday, 12 Oct 2026"
    val kickoffTime: String, // e.g. "20:00"
    val section: String, // "FREE" or "VIP"
    val vipCategory: String?, // "2_plus", "3_plus", "5_plus", "special", or null for FREE
    val resultState: String, // "PENDING", "WON", "LOST"
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    val isPending: Boolean get() = resultState == PredictionResult.PENDING.name
    val isWon: Boolean get() = resultState == PredictionResult.WON.name
    val isLost: Boolean get() = resultState == PredictionResult.LOST.name
}

data class DateGroupedPredictions(
    val matchDate: String,
    val displayDate: String,
    val predictions: List<PredictionEntity>,
    val totalGames: Int,
    val accumulatedOdds: Double
)
