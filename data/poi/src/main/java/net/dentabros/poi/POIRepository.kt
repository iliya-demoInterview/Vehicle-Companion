package net.dentabros.poi

import kotlinx.coroutines.flow.Flow

interface POIRepository {
    suspend fun getPOIs(): Result<List<POI>>
    fun getFavourites(): Flow<List<POI>>
    suspend fun insert(poi: POI)
}