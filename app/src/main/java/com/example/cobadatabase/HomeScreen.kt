package com.example.cobadatabase

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    onNextButtonClick: () -> Unit
){
    var users = userViewModel.users.collectAsState()
    Column {
        Button(
            onClick = onNextButtonClick,
            modifier = Modifier.size(80.dp,80.dp)
        ) {
            Text(text = "Go To Another Page")
        }
        LazyColumn{
            items(users.value){
                Text(text = it.firstName)
            }
        }
    }
}