package com.ihfazh.dailytrackerchild.pages.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ihfazh.dailytrackerchild.fp.Failure
import com.ihfazh.dailytrackerchild.fp.Success
import com.ihfazh.dailytrackerchild.remote.DummyClient
import com.ihfazh.dailytrackerchild.remote.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


typealias OnLoginSuccess = () -> Unit

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: OnLoginSuccess,
    modifier: Modifier = Modifier
){
    val state = viewModel.state.collectAsState()


    Login(
        state = state.value,
        modifier = modifier,
        onLoginClicked = { username, password ->
            viewModel.onLoginClicked(username, password)
        }
    )

    LaunchedEffect(state.value){
        if (state.value is LoginSuccess){
            onLoginSuccess()
        }
    }
}

