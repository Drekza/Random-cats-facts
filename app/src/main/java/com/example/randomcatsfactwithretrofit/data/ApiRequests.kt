package com.example.randomcatsfactwithretrofit.data

import com.example.randomcatsfactwithretrofit.api.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("/facts/random")
    fun getCatFact(): Call<CatJson>
}