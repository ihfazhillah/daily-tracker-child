package com.ihfazh.dailytrackerchild.pages.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


typealias OnLoginClicked = (username: String, password: String) -> Unit

@Composable
fun Login(state: LoginState, modifier: Modifier = Modifier, onLoginClicked: OnLoginClicked = {u, p -> }){

    var username by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    val enabledButton = !(username.isEmpty() && password.isEmpty()) and  (state !is Submitting)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){

        Text(text = "Login", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(40.dp))

        if ((state is IdleLoginState) && (state.error !== null)){
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(16.dp)

            )
        }
        Spacer(Modifier.height(40.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Username", style = MaterialTheme.typography.labelLarge)
                TextField(value = username, onValueChange = { username = it },  placeholder = {Text("Username")})
            }

            Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
                Text(text = "Password", style = MaterialTheme.typography.labelLarge)
                TextField(value = password, onValueChange = { password = it }, visualTransformation = PasswordVisualTransformation())
            }

            ElevatedButton(onClick = {onLoginClicked.invoke(username, password)}, enabled = enabledButton) {
                if (state is Submitting){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(MaterialTheme.typography.bodyMedium.fontSize.value.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(text = "Login")
            }


        }

    }

}


@Preview(widthDp = 1280, heightDp = 800, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun LoginPreview(){
    Login(Submitting)
}