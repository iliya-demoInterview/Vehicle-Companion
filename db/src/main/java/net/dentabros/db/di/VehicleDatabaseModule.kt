package net.dentabros.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import net.dentabros.db.VehicleDatabase
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import net.dentabros.db.VehicleDAO

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): VehicleDatabase {
        return Room.databaseBuilder(
            context,
            VehicleDatabase::class.java,
            "vehicle.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideVehicleDAO(vehicleDatabase: VehicleDatabase): VehicleDAO =
        vehicleDatabase.getVehicleDAO()

}