package net.dentabros.vehicle.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.dentabros.vehicle.VehicleRepository
import net.dentabros.vehicle.VehicleRepositoryImplementation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface VehicleModule {

    @Singleton
    @Binds
    fun bindsVehicleRepository(
        vehicleRepository: VehicleRepositoryImplementation,
    ): VehicleRepository

}
