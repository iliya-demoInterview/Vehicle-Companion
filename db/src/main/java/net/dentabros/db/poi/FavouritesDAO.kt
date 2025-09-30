package net.dentabros.db.poi

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.dentabros.db.vehicle.VehicleEntity

@Dao
interface FavouritesDAO {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(poi: POIEntity)

    @Delete
    suspend fun delete(poi: POIEntity)

    @Query("select * from poi")
    fun getPois() : Flow<List<POIEntity>>
}