package com.example.cricketapp.api

import androidx.databinding.library.BuildConfig
import com.example.cricketapp.api.network.CoroutineAdapterFactory
import com.example.cricketapp.data.MatchDetailsData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
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
    .baseUrl("https://demo.sportz.io/")
    .addCallAdapterFactory(CoroutineAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface APIService {
    //get profile image url
    @GET("nzin01312019187360.json")
    suspend fun getMatchDetailsAPI1(): MatchDetailsData

    @GET("sapk01222019186652.json")
    suspend fun getMatchDetailsAPI2(): MatchDetailsData
}

object CricketAPPAPI {
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}