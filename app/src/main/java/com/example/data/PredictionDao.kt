package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.model.PredictionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PredictionDao {

    @Query("SELECT * FROM predictions ORDER BY matchDate ASC, kickoffTime ASC")
    fun getAllPredictions(): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE section = 'FREE' ORDER BY matchDate ASC, kickoffTime ASC")
    fun getFreePredictions(): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE section = 'VIP' AND vipCategory = :vipCategory AND resultState = 'PENDING' ORDER BY matchDate ASC, kickoffTime ASC")
    fun getActiveVipPredictions(vipCategory: String): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE section = 'VIP' AND vipCategory = :vipCategory AND resultState != 'PENDING' ORDER BY matchDate DESC, kickoffTime DESC")
    fun getHistoryVipPredictions(vipCategory: String): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE id = :id LIMIT 1")
    suspend fun getPredictionById(id: String): PredictionEntity?

    @Query("SELECT COUNT(*) FROM predictions")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(predictions: List<PredictionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prediction: PredictionEntity)

    @Update
    suspend fun update(prediction: PredictionEntity)

    @Query("UPDATE predictions SET resultState = :resultState, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateResult(id: String, resultState: String, updatedAt: Long)

    @Query("DELETE FROM predictions")
    suspend fun clearAll()
}
