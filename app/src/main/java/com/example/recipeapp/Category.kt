package com.example.recipeapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
): Parcelable

data class CategoryResponse(
    val categories : List<Category>
)

@Entity("category_table")
data class OfflineCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)