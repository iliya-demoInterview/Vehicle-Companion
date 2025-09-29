package net.dentabros.poi

import net.dentabros.network.POIDTO
import javax.inject.Inject

class DTOToPoiMapper @Inject constructor() : (POIDTO) -> POI {
    override fun invoke(dto: POIDTO): POI =
        POI(
            id = dto.id,
            name = dto.name,
            url = dto.url,
            imageUrl = dto.imageUrl,
            rating = dto.rating,
            primaryCategory = dto.primaryCategory,
        )
}