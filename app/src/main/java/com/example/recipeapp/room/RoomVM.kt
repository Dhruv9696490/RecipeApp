package com.example.recipeapp.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Category
import com.example.recipeapp.OfflineCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomVM(private val repository: RecipeRepository = Graph.repository): ViewModel(){
    val getAllRecipe = repository.getAllRecipe()
    fun addRecipe(recipe: List<OfflineCategory>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addRecipe(recipe)
        }
    }
}