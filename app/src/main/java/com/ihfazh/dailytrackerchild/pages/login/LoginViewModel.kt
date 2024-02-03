package com.ihfazh.dailytrackerchild.pages.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ihfazh.dailytrackerchild.DailyTrackerChildApplication
import com.ihfazh.dailytrackerchild.fp.Failure
import com.ihfazh.dailytrackerchild.fp.Success
import com.ihfazh.dailytrackerchild.remote.DummyClient
import com.ihfazh.dailytrackerchild.remote.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val client: DummyClient
): ViewModel() {

    private var _state = MutableStateFlow<LoginState>(IdleLoginState(null))
    val state = _state.asStateFlow()

    fun onLoginClicked(username: String, password: String){
        _state.value = Submitting
        viewModelScope.launch(Dispatchers.IO){
            val outcome = client.login(LoginBody(username, password))
            _state.value = when(outcome){
                is Success -> {
                    // we should not know about token process here, the client should handle this
                    LoginSuccess
                }

                is Failure -> {
                    IdleLoginState(outcome.error.msg)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY]) as DailyTrackerChildApplication
                return LoginViewModel(application.compositionRoot.client) as T
            }
        }
    }


}