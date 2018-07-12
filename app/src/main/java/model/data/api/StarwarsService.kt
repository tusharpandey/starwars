package model.data.api

import model.data.pojo.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarsService {
    @GET("api/people/")
    fun getNewestWallpapers(@Query("page") page: Int): Call<Characters>
}