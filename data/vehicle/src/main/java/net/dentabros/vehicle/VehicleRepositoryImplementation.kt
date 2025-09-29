package net.dentabros.vehicle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.dentabros.db.vehicle.VehicleDAO
import javax.inject.Inject

internal class VehicleRepositoryImplementation @Inject constructor(
    private val vehicleDAO: VehicleDAO,
    private val vehicleMapper: VehicleMapper,
    private val vehicleEntityMapper: VehicleEntityMapper,
    private val mutableVehicleMapper: MutableVehicleMapper,
) : VehicleRepository {
    override fun getVehicles(): Flow<List<Vehicle>> =
        vehicleDAO.getVehicles().map { vehicleEntities -> vehicleEntities.map(vehicleMapper) }

    override suspend fun getVehicle(id: Long): MutableVehicle =
        mutableVehicleMapper(vehicleDAO.getVehicle(id))

    override suspend fun insert(vehicle: MutableVehicle) {
        vehicleDAO.insert(vehicleEntityMapper(vehicle))
    }

    override suspend fun update(vehicle: MutableVehicle) {
        vehicleDAO.update(vehicleEntityMapper(vehicle))
    }


}