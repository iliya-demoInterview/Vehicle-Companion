package net.dentabros.network

import retrofit2.http.GET

internal interface API {
    @GET("pois/discover?sw_corner=-84.540499,39.079888&ne_corner=-84.494260,39.113254&page_size=50")
    suspend fun getPois(): PoiResponse
}