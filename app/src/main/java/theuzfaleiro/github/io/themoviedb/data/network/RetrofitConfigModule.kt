package theuzfaleiro.github.io.themoviedb.data.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import theuzfaleiro.github.io.themoviedb.BuildConfig
import theuzfaleiro.github.io.themoviedb.TheMovieDbApplication
import theuzfaleiro.github.io.themoviedb.util.Rx.AppScheduler
import theuzfaleiro.github.io.themoviedb.util.Rx.RxSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitConfigModule {

    @Provides
    @Reusable
    fun providesGitHubEndPoint(retrofit: Retrofit): TheMovieDbEndpoint =
            retrofit.create(TheMovieDbEndpoint::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory,
                         trendingOnGitHubApplication: TheMovieDbApplication,
                         rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(trendingOnGitHubApplication.getBaseUrl())
            .build()


    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, okHttpCache: Cache): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .cache(okHttpCache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesOkHttpCache(trendingOnGitHubApplication: TheMovieDbApplication): Cache =
            Cache(trendingOnGitHubApplication.cacheDir, 10 * 1024 * 1024)

    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    fun providesRxSchedulers(): RxSchedulers = AppScheduler()

    @Provides
    fun providesBuildType(): String = BuildConfig.BUILD_TYPE

    @Provides
    fun providesHttpLoggingInterceptor(buildType: String): HttpLoggingInterceptor {
        return if (buildType.contentEquals("debug")) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }
}