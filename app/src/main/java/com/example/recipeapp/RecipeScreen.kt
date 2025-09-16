package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.room.RoomVM

@Composable
fun RecipeScreenHai(viewModel: RecipeViewModel,navigateToSecond:(Category)->Unit){
    val roomVm: RoomVM = viewModel()
     Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp),
         horizontalAlignment = Alignment.CenterHorizontally){
         val categoryState = viewModel.categoryState.value
         Button(onClick = {
             viewModel.fetchCategory(roomVm)
         }){
             Text("Get Recipe")
         }
        when{
            (categoryState.loading)-> {
                CircularProgressIndicator(strokeWidth = 32.dp)
            }
            (categoryState.error!=null && categoryState.error!="offline")->{
                Text("Error 404",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray)
            }
            (categoryState.recipeList.isNotEmpty() || categoryState.error=="offline")->{
                if(categoryState.error=="offline") Text("Offline")
                CategoryScreen(categoryState.recipeList,navigateToSecond)
            }
        }
    }
}
@Composable
fun CategoryScreen(categoryList: List<Category>,navigateToSecond: (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(2)){
        items(categoryList){
            CategoryItem(it,navigateToSecond)
        }
    }
}
@Composable
fun CategoryItem(category: Category,navigateToSecond: (Category) -> Unit){
    Column(modifier = Modifier.fillMaxSize().padding(8.dp).clickable(onClick = {navigateToSecond(category)}),
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f))
            Text(category.strCategory, fontSize = 32.sp,
                fontWeight = FontWeight.Bold)
    }
}