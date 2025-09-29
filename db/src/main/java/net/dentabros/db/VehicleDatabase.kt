package net.dentabros.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.dentabros.db.poi.FavouritesDAO
import net.dentabros.db.poi.POIEntity
import net.dentabros.db.vehicle.VehicleDAO
import net.dentabros.db.vehicle.VehicleEntity


@Database(version = 1, entities = [VehicleEntity::class, POIEntity::class], exportSchema = false)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun getVehicleDAO(): VehicleDAO
    abstract fun getFavouritesDAO() : FavouritesDAO
}