package charleston.androidkotlinproject.di.modules

import charleston.androidkotlinproject.data.remote.features.info.InfoManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by charleston.anjos on 03/10/17.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGsonFactory(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonFactory: Gson, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://charleston.us-2.evennode.com/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonFactory))
                .client(httpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideInfoManager(retrofit: Retrofit): InfoManager {
        return InfoManager(retrofit)
    }
}