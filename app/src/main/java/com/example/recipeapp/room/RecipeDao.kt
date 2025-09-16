package com.example.recipeapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.Category
import com.example.recipeapp.OfflineCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao{
    @Query("SELECT * FROM category_table")
    fun getAllRecipe(): Flow<List<OfflineCategory>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: List<OfflineCategory>)
}