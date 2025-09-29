package net.dentabros.network

import javax.inject.Inject

internal class PoiServiceImp @Inject constructor(private val api: API): PoiService {
    override suspend fun getPois(): Result<List<POIDTO>> =
        runCatching {
            val response = api.getPois()
            response.pois
        }
}