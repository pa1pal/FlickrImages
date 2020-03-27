package pa1pal.flickrimages.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pa1pal.flickrimages.model.Images
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @GET(".")
    fun searchImages(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String = "062a6c0c49e4de1d78497d13a7dbb360",
        @Query("text") text: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("per_page") perpage: Int = 15,
        @Query("page") page: Int = 1
    ) : Call<Images>

    companion object {
        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.flickr.com/services/rest/")
                .client(OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }.build())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }
}