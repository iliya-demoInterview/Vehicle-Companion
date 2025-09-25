package net.dentabros.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDAO {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(vehicle: VehicleEntity)

    @Query("select * from vehicle")
    fun getVehicles() : Flow<List<VehicleEntity>>

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(vehicle: VehicleEntity)

    @Query("select * from vehicle where id = :id")
    suspend fun getVehicle(id: Long) : VehicleEntity

}