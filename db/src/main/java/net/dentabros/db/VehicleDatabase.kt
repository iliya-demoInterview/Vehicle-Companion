package net.dentabros.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(version = 1, entities = [VehicleEntity::class], exportSchema = false)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun getVehicleDAO(): VehicleDAO
}