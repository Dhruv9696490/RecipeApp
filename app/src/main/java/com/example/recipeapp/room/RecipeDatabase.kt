package com.example.recipeapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.Category
import com.example.recipeapp.OfflineCategory

@Database(
    entities = [OfflineCategory::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase: RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
}