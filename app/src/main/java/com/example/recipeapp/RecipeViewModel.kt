package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeViewModel: ViewModel(){
    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoryState
    init {
        fetchCategory()
    }
    private fun fetchCategory(){
        viewModelScope.launch {
        try{
            val responseList=RecipeService.getCategory()
            _categoryState.value=_categoryState.value.copy(
                loading = false,
                recipeList = responseList.categories
            )
        }catch(e: Exception){
            _categoryState.value=_categoryState.value.copy(
                loading = false,
                error="error"
            )
        } }
    }
    data class RecipeState(
        val loading: Boolean=true,
        val error: String?=null,
        val recipeList: List<Category> =emptyList()
    )
}

