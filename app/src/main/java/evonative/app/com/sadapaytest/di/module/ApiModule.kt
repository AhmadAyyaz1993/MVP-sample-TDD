package evonative.app.com.sadapaytest.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import evonative.app.com.sadapaytest.api.ReposApi
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getPostApi(retrofit: Retrofit): ReposApi = retrofit.create(ReposApi::class.java)
}
