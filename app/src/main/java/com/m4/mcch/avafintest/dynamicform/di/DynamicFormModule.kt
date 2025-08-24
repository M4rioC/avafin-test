package com.m4.mcch.avafintest.dynamicform.di

import com.m4.mcch.avafintest.dynamicform.data.DynamicFormRepositoryImpl
import com.m4.mcch.avafintest.dynamicform.data.remote.DynamicFormApi
import com.m4.mcch.avafintest.dynamicform.domain.repository.DynamicFormRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module that provides the dependencies for the dynamic form feature.
 */
@Module
@InstallIn(SingletonComponent::class)
object DynamicFormModule {

    private const val BASE_URL = "https://drive.google.com/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesDynamicFormApi(retrofit: Retrofit): DynamicFormApi {
        return retrofit.create(DynamicFormApi::class.java)
    }

    @Provides
    fun providesDynamicFormRepository(api: DynamicFormApi): DynamicFormRepository {
        return DynamicFormRepositoryImpl(api)
    }

}