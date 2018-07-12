package model.data.api

import model.callbacks.OnCharacterListReceived
import model.data.pojo.Characters
import model.data.roomdb.CharacterNames
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import view.charaterlist.CharacterListActivity
import java.util.concurrent.TimeUnit


class RetrofitRequestBuilder {

    var BASE_URL = "https://swapi.co/"

    var api: StarwarsService
    var retrofit: Retrofit

    init {
        val client = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = createRetrofitInstance()
    }

    private fun createRetrofitInstance(): StarwarsService {
        return getRetrofitInstance()
    }

    private fun getRetrofitInstance(): StarwarsService {
        return retrofit.create(StarwarsService::class.java)
    }

    fun getCharacterList(pageCount: Int, callback: OnCharacterListReceived) {
        val call = api.getNewestWallpapers(pageCount)
        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                var characters: Characters
                if (response.body() == null) {
                    characters = Characters()
                } else {
                    characters = response.body() as Characters
                }
                callback.onCharacterListReceived(characters)

                if (pageCount == 1) {
                    CharacterListActivity.dbInstance.characterNames().deleteData()
                    var list: ArrayList<CharacterNames> = ArrayList()
                    for (result in characters.results) {
                        var characterName = CharacterNames()
                        characterName.name = result.name
                        list.add(characterName)
                    }
                    CharacterListActivity.dbInstance.characterNames().insert(list)
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                callback.onError(t)
            }
        })
    }


}