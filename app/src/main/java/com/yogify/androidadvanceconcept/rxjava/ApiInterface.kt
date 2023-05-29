package com.yogify.androidadvanceconcept.rxjava

import com.yogify.androidadvanceconcept.rxjava.APICONSTANT.PRODUCTS
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET(PRODUCTS)
    fun getProduct():Observable<List<Product>>
}