package com.example.recipeapp.room

import com.example.recipeapp.Category
import com.example.recipeapp.OfflineCategory
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao){
    fun getAllRecipe(): Flow<List<OfflineCategory>> = recipeDao.getAllRecipe()
    suspend fun addRecipe(recipe: List<OfflineCategory>) = recipeDao.addRecipe(recipe)
}