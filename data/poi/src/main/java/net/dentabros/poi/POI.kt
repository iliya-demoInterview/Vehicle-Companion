package net.dentabros.poi

data class POI (
    val id: Int,
    val name: String?,
    val primaryCategory: String,
    val url : String,
    val rating: Double,
    val imageUrl: String
){
}