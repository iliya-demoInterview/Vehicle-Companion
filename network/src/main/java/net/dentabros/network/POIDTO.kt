package net.dentabros.network

import com.google.gson.annotations.SerializedName

data class POIDTO (
    val id: Int,
    val name: String?,
    @SerializedName("primary_category_display_name")
    val primaryCategory: String,
    val url : String,
    val rating: Double,
    @SerializedName("v_320x320_url")
    val imageUrl: String
){
}