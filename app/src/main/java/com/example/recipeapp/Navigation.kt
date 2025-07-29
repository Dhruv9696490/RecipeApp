package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val viewModel: RecipeViewModel= viewModel()
    val recipeState= viewModel.categoryState.value
    val navController= rememberNavController()
    NavHost(navController,Routes.RecipeScreen.routes){
        composable(Routes.RecipeScreen.routes){
           RecipeScreenHai(recipeState, navigateToSecond = {it->
               navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
               navController.navigate(Routes.DescriptionScreen.routes)
           })
        }
        composable(Routes.DescriptionScreen.routes){
            val category=navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?: Category("","","","")
            DetailScreen(category)
        }
    }
}