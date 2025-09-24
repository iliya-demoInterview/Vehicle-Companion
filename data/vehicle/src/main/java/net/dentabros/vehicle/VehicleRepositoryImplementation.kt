package net.dentabros.vehicle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.dentabros.db.VehicleDAO
import javax.inject.Inject

internal class VehicleRepositoryImplementation @Inject constructor(
    private val vehicleDAO: VehicleDAO,
    private val vehicleMapper: VehicleMapper,
    private val vehicleEntityMapper: VehicleEntityMapper
) : VehicleRepository {
    override fun getVehicles(): Flow<List<Vehicle>> =
        vehicleDAO.getVehicles().map { vehicleEntities -> vehicleEntities.map(vehicleMapper) }

    override suspend fun insert(vehicle: ModifyVehicle) {
        vehicleDAO.insert(vehicleEntityMapper(vehicle))
    }
}