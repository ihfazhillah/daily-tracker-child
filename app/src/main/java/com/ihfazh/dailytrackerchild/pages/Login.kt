package com.ihfazh.dailytrackerchild.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Login(modifier: Modifier = Modifier){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){

        Text(text = "Login", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(40.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Username", style = MaterialTheme.typography.labelLarge)
                TextField(value = "", onValueChange = {},  placeholder = {Text("Username")})
            }

            Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
                Text(text = "Password", style = MaterialTheme.typography.labelLarge)
                TextField(value = "Hello world", onValueChange = {}, visualTransformation = PasswordVisualTransformation())
            }

            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Login")
            }


        }

    }

}


@Preview(widthDp = 1280, heightDp = 800, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun LoginPreview(){
    Login()
}