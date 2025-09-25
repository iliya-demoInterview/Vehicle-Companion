package net.dentabros.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle")
data class VehicleEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String?,
    val make: String?,
    val model: String?,
    val year : Int,
    val VIN: String?,
    val fuelType: String?,
    val uri: String?
)