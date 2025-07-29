package com.example.recipeapp

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val _retrofit= Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

val RecipeService =_retrofit.create(RecipeApi::class.java)
  interface RecipeApi{
      @GET("categories.php")
      suspend fun getCategory(): Response<CategoryResponse>
  }