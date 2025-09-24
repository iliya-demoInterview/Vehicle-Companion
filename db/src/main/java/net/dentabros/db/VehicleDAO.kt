package net.dentabros.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDAO {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(vehicle: VehicleEntity)

    @Query("select * from vehicle")
    fun getVehicles() : Flow<List<VehicleEntity>>

//    @Query("Select * From movieDetail Where id = :id")
//    suspend fun getMovieDetailById(id: Int): MovieDetail?

//    @Query("DELETE FROM movieDetail WHERE id = :id")
//    suspend fun deleteMovieDetailById(id: Int)

//
//    @Query("SELECT * FROM movieDetail")
//    suspend fun getAllMovieDetails(): List<MovieDetail?>
}