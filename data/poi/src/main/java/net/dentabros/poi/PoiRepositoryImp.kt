package net.dentabros.poi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.dentabros.db.poi.FavouritesDAO
import net.dentabros.network.PoiService
import javax.inject.Inject

internal class PoiRepositoryImp @Inject constructor(
    private val poiService: PoiService,
    private val favouritesDAO: FavouritesDAO,
    private val dtoToPoiMapper: DTOToPoiMapper,
    private val entityToPoiMapper: EntityToPoiMapper,
    private val poiToEntityMapper: PoiToEntityMapper,
) : POIRepository {
    override suspend fun getPOIs(): Result<List<POI>> =
        poiService.getPois().map {
            it.map(dtoToPoiMapper)
        }

    override fun getFavourites(): Flow<List<POI>> =
        favouritesDAO.getPois().map { it.map(entityToPoiMapper) }

    override suspend fun insert(poi: POI) {
        favouritesDAO.insert(poiToEntityMapper(poi))
    }

    override suspend fun delete(poi: POI) {
        favouritesDAO.delete(poiToEntityMapper(poi))
    }


}