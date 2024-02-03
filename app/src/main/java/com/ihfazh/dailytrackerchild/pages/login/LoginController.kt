package com.ihfazh.dailytrackerchild.pages.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.ihfazh.dailytrackerchild.fp.Failure
import com.ihfazh.dailytrackerchild.fp.Success
import com.ihfazh.dailytrackerchild.remote.DummyClient
import com.ihfazh.dailytrackerchild.remote.LoginBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class LoginController(
    val client: DummyClient,
    val onLoginSuccess: OnLoginSuccess
){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    var state: LoginState by mutableStateOf(IdleLoginState(null))


    fun onStart(){

    }

    fun onStop(){
        coroutineScope.coroutineContext.cancelChildren()
    }

    val onLoginClicked: (username: String, password: String) -> Unit = { username, password ->
        state = state.submit()
        coroutineScope.launch(Dispatchers.IO) {
            val outcome = client.login(LoginBody(username, password))
            when(outcome){
                is Success -> {
                    onLoginSuccess.invoke()
                }
                is Failure -> {
                    state = state.error(outcome.error.msg)
                }
            }
        }

    }

}


private fun LoginState.error(msg: String): LoginState = IdleLoginState(msg)
private fun LoginState.submit(): LoginState = Submitting
