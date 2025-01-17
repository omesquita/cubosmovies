package br.com.osnirmesquita.cubosmovies.di

import br.com.osnirmesquita.cubosmovies.BuildConfig
import br.com.osnirmesquita.cubosmovies.data.remote.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val CONNECT_TIME_OUT: Long = 15
private const val READ_TIME_OUT: Long = 10

val networkModule = module {
    /**
     * Provides a single instance to retrofit api.
     * */
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_API_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    /**
     * Provide a single instance of the httpclient
     * */
    single {
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor(authInterceptor())
            .build()
    }
}

/**
 * Provides a [HttpLoggingInterceptor] to tracker logs with timber.
 *
 * @return httpLoggingInterceptor
 * */
private fun httpLoggingInterceptor() = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d(it) })
    .setLevel(HttpLoggingInterceptor.Level.BODY)

/**
 * Create [Interceptor] to add moviedb api key in all connections.
 *
 * @return interceptor
 * */
private fun authInterceptor() = Interceptor {
    val origin = it.request()
    val originHttpUrl = origin.url()

    //add api_key parameter in the end url
    val url = originHttpUrl.newBuilder()
        .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
        .addQueryParameter("language", "pt-BR")
        .build()

    val requestBuilder = origin.newBuilder().url(url)
    val request = requestBuilder.build()
    it.proceed(request)
}