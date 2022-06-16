package kr.hs.dgsw.history.imtest.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Provides
    @Singleton
    fun provideKTwikiApi(): KTwikiApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KTwikiApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: KTwikiApi): KTwikiRepository {
        return KTwikiRepositoryImpl(api)
    }*/
}