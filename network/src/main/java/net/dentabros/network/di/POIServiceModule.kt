package net.dentabros.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.dentabros.network.PoiService
import net.dentabros.network.PoiServiceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface POIServiceModule {

    @Singleton
    @Binds
    fun bindsPoiService(
        service: PoiServiceImp,
    ): PoiService

}