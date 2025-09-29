package net.dentabros.network

interface PoiService {
    suspend fun getPois() : Result<List<POIDTO>>
}