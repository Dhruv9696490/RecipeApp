package com.example.recipeapp.room

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: RecipeDatabase
    fun createRoomDB(context: Context){
        database= Room.databaseBuilder(context, RecipeDatabase::class.java,"recipe.db").build()
    }
    val repository by lazy {
        RecipeRepository(database.recipeDao())
    }
}