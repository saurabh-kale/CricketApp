package com.example.cricketapp.api

import androidx.databinding.library.BuildConfig
import com.example.cricketapp.api.network.CoroutineAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val requestInterceptor = Interceptor { chain ->
    val url = chain.request()
        .url
        .newBuilder()
        .build()

    val request = chain
        .request()
        .newBuilder()
        .url(url)
        .build()

    return@Interceptor chain.proceed(request)
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().connectTimeout(50, TimeUnit.SECONDS)
        .readTimeout(50, TimeUnit.SECONDS).writeTimeout(50, TimeUnit.SECONDS)
        .run {
            addInterceptor(interceptor)
        }
        .addInterceptor(requestInterceptor)
        .addInterceptor(interceptor)
        .build()

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply(fun HttpLoggingInterceptor.() {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.BODY
    })
}

val httpLogging = provideLoggingInterceptor()
val okHttpClient = provideOkHttpClient(httpLogging)

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
//    .baseUrl(AppData.URL)
    .addCallAdapterFactory(CoroutineAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface APIService {

}

object CricketAPPAPI {
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}