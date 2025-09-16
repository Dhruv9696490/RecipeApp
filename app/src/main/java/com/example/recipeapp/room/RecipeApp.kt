package com.example.recipeapp.room

import android.app.Application

class RecipeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.createRoomDB(this)
    }
}