package com.yogify.androidadvanceconcept.FlowWithMVVM

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(APICONSTANT.PRODUCTS)
    suspend fun getProduct(): Response<List<Product>>
}