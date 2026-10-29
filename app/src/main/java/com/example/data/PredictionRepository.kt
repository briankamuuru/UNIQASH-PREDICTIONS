package com.example.data

import com.example.model.PredictionEntity
import com.example.model.PredictionResult
import com.example.model.VipCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PredictionRepository(private val dao: PredictionDao) {

    val freePredictions: Flow<List<PredictionEntity>> = dao.getFreePredictions()

    fun getActiveVipPredictions(category: VipCategory): Flow<List<PredictionEntity>> {
        return dao.getActiveVipPredictions(category.storageKey)
    }

    fun getHistoryVipPredictions(category: VipCategory): Flow<List<PredictionEntity>> {
        return dao.getHistoryVipPredictions(category.storageKey)
    }

    suspend fun updateResult(id: String, result: PredictionResult) {
        withContext(Dispatchers.IO) {
            dao.updateResult(id, result.name, System.currentTimeMillis())
        }
    }

    suspend fun ensureSeeded() {
        withContext(Dispatchers.IO) {
            val count = dao.count()
            if (count == 0) {
                dao.insertAll(SampleData.getInitialPredictions())
            }
        }
    }

    suspend fun resetDemoData() {
        withContext(Dispatchers.IO) {
            dao.clearAll()
            dao.insertAll(SampleData.getInitialPredictions())
        }
    }
}
