package net.dentabros.vehicle

import androidx.core.net.toUri
import net.dentabros.db.VehicleEntity
import javax.inject.Inject

internal class MutableVehicleMapper @Inject constructor() : (VehicleEntity) -> MutableVehicle {
    override fun invoke(entity: VehicleEntity): MutableVehicle =
        MutableVehicle(
            id = entity.id,
            name = entity.name ?: "",
            make = entity.make ?: "",
            model = entity.model ?: "",
            year = entity.year,
            VIN = entity.VIN ?: "",
            fuelType = entity.fuelType ?: "",
            uri = entity.uri?.toUri()
        )
}