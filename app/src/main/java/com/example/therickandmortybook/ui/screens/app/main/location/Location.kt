package com.example.therickandmortybook.ui.screens.app.main.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.therickandmortybook.data.model.locatiion.ResultDta
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationScreen(
    viewModel: LocationViewModel = getViewModel(),
    onItemClick: (Int?) -> Unit
) {
    val pagingItems = viewModel.locationFlow.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        items(pagingItems.itemCount) { index ->
            val location = pagingItems[index]
            location.let {
                LocationItem(
                    location = it,
                    onItemClick = { id ->
                        id.let { onItemClick(it) }
                    })
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun LocationItem(
    location: ResultDta?,
    onItemClick: (Int?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                location?.id?.let { id ->
                    onItemClick(id)
                }
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Text(
                text = location?.name ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}