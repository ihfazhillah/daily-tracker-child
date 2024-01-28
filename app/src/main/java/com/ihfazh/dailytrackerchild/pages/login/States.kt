package com.ihfazh.dailytrackerchild.pages.login

sealed interface LoginState{}
data class IdleLoginState(
    val error: String?
): LoginState


data object Submitting: LoginState