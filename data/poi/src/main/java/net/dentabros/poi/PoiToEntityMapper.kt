package net.dentabros.poi

import net.dentabros.db.poi.POIEntity
import javax.inject.Inject


class PoiToEntityMapper @Inject constructor() : (POI) -> POIEntity {
    override fun invoke(poi: POI): POIEntity =
        POIEntity(
            id = poi.id,
            name = poi.name,
            url = poi.url,
            imageUrl = poi.imageUrl,
            rating = poi.rating,
            primaryCategory = poi.primaryCategory,
        )
}