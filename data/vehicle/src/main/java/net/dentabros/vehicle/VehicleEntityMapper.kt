package net.dentabros.vehicle

import net.dentabros.db.VehicleEntity
import javax.inject.Inject

internal class VehicleEntityMapper @Inject constructor() : (MutableVehicle) -> VehicleEntity {
    override fun invoke(vehicle: MutableVehicle): VehicleEntity =
        VehicleEntity(
            id = vehicle.id,
            name = vehicle.name.ifEmpty { null },
            make = vehicle.make.ifEmpty { null },
            model = vehicle.model.ifEmpty { null },
            year = vehicle.year,
            VIN = vehicle.VIN.ifEmpty { null },
            fuelType = vehicle.fuelType.ifEmpty { null },
            uri = vehicle.uri.toString()
        )
}