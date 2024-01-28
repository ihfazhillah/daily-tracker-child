package com.ihfazh.dailytrackerchild.pages.login

interface LoginState{}
data class IdleLoginState(
    val error: String?
): LoginState


object Submitting: LoginState