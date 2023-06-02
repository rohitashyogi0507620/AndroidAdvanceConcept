package com.yogify.androidadvanceconcept.rxjava

import com.yogify.androidadvanceconcept.FlowWithMVVM.APICONSTANT.PRODUCTS
import com.yogify.androidadvanceconcept.FlowWithMVVM.Product
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET(PRODUCTS)
    fun getProduct():Observable<List<Product>>

    @GET(PRODUCTS)
    fun getProductFlow():Observable<List<Product>>
}