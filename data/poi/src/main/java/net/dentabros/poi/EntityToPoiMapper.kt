package net.dentabros.poi

import net.dentabros.db.poi.POIEntity
import javax.inject.Inject

class EntityToPoiMapper @Inject constructor() : (POIEntity) -> POI {
    override fun invoke(entity: POIEntity): POI =
        POI(
            id = entity.id,
            name = entity.name,
            url = entity.url,
            imageUrl = entity.imageUrl,
            rating = entity.rating,
            primaryCategory = entity.primaryCategory,
        )
}