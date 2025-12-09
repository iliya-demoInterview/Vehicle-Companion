package net.dentabros.socket.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.dentabros.socket.SocketService
import net.dentabros.socket.SocketServiceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SocketServiceModule {

    @Singleton
    @Binds
    fun bindSocketService(
        service: SocketServiceImp,
    ): SocketService

}