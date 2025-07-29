package com.example.recipeapp

sealed class Routes(val routes: String) {
    object RecipeScreen: Routes("Recipe")
    object DescriptionScreen: Routes("Description")
}