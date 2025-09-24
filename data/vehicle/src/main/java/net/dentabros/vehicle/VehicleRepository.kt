package net.dentabros.vehicle

import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getVehicles(): Flow<List<Vehicle>>
    suspend fun insert(vehicle: ModifyVehicle)
}