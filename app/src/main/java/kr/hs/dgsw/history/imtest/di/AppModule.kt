package kr.hs.dgsw.history.imtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.history.imtest.common.Constants
import kr.hs.dgsw.history.imtest.data.remote.api.ImtestApi
import kr.hs.dgsw.history.imtest.data.repository.ImtestRepositoryImpl
import kr.hs.dgsw.history.imtest.domain.repository.ImtestRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideImtestApi(): ImtestApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImtestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImtestRepository(api: ImtestApi): ImtestRepository {
        return ImtestRepositoryImpl(api)
    }
}