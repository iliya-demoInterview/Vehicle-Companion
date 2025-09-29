package net.dentabros.poi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.dentabros.poi.POIRepository
import net.dentabros.poi.PoiRepositoryImp
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface POIModule {

    @Singleton
    @Binds
    fun bindsPOIRepository(
        poiRepository: PoiRepositoryImp,
    ): POIRepository

}