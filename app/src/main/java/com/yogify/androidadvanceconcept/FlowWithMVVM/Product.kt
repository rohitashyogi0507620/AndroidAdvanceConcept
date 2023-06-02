package com.yogify.androidadvanceconcept.FlowWithMVVM


data class Product(
    var category: String,
    var description: String,
    var id: Int,
    var image: String,
    var price: Double,
    var rating: Rating,
    var title: String
)