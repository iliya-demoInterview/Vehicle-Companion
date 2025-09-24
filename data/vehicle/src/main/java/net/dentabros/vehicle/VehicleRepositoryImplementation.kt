package net.dentabros.vehicle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.dentabros.db.VehicleDAO
import javax.inject.Inject

internal class VehicleRepositoryImplementation @Inject constructor(
    private val vehicleDAO: VehicleDAO,
    private val vehicleMapper: VehicleMapper
) : VehicleRepository {
    override fun getVehicles(): Flow<List<Vehicle>> =
        vehicleDAO.getVehicles().map { vehicleEntities -> vehicleEntities.map(vehicleMapper) }
}