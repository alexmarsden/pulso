package com.example.pulso.hilt.di

import android.content.Context
import com.example.pulso.data.HealthServicesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun provideHealthServices(@ApplicationContext context: Context): HealthServicesRepository = HealthServicesRepository(context)
}