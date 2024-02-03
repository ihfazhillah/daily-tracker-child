package com.ihfazh.dailytrackerchild.pages.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface LoginState

@Parcelize
data class IdleLoginState(
    val error: String?
): LoginState, Parcelable


@Parcelize
data object Submitting: LoginState, Parcelable

@Parcelize
data object LoginSuccess: LoginState, Parcelable
