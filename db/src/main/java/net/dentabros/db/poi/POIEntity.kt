package net.dentabros.db.poi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poi")
data class POIEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val primaryCategory: String,
    val url : String,
    val rating: Double,
    val imageUrl: String
){}