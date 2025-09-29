package net.dentabros.vehicle

import androidx.core.net.toUri
import net.dentabros.db.vehicle.VehicleEntity
import javax.inject.Inject

internal class VehicleMapper @Inject constructor() : (VehicleEntity) -> Vehicle {
    override fun invoke(entity: VehicleEntity): Vehicle =
        Vehicle(
            id = entity.id,
            name = entity.name,
            make = entity.make,
            model = entity.model,
            year = entity.year,
            VIN = entity.VIN,
            fuelType = entity.fuelType,
            uri = entity.uri?.toUri()
        )
}