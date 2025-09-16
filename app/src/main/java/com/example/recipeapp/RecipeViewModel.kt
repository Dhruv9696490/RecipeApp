package com.example.recipeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.room.RoomVM
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeViewModel: ViewModel(){
    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoryState
     fun fetchCategory(roomVM: RoomVM){
        viewModelScope.launch {
            _categoryState.value=_categoryState.value.copy(
                loading = true
            )
        try{
            val responseList=RecipeService.getCategory().body()?.categories
            Log.d("error dikhao1",responseList?.size.toString())
            responseList?.let {respo->
                _categoryState.value=_categoryState.value.copy(
                    loading = false,
                    recipeList = respo
                )
                val offlineRecipe = respo.map {recipe->
                    OfflineCategory(
                        idCategory = recipe.idCategory ?: "",
                        strCategory = recipe.strCategory ?: "",
                        strCategoryThumb = recipe.strCategoryThumb ?: "",
                        strCategoryDescription = recipe.strCategoryDescription ?: "",
                    )
                }
                Log.d("error dikhao2",offlineRecipe?.size.toString())
                roomVM.addRecipe(offlineRecipe)
            }
        }catch(e: Exception){
            val offlineRecipe = roomVM.getAllRecipe.first()
            Log.d("error dikhao3",offlineRecipe?.size.toString())
            val recipe = offlineRecipe.map {it->
                Category(
                    idCategory = it.idCategory ?: "",
                    strCategory = it.strCategory ?: "",
                    strCategoryThumb = it.strCategoryThumb ?:"",
                    strCategoryDescription = it.strCategoryDescription ?: ""
                )
            }
            Log.d("error dikhao4",recipe?.size.toString())
            _categoryState.value=_categoryState.value.copy(
                loading = false,
                error="offline",
                recipeList = recipe
            )
        } }
    }
    data class RecipeState(
        val loading: Boolean=false,
        val error: String?=null,
        val recipeList: List<Category> =emptyList()
    )
}

