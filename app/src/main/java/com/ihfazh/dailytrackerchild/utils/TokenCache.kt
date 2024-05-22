package com.ihfazh.dailytrackerchild.utils

class TokenCache
{

    private var _token : String? = null

    private val tokenKey = "userToken"

    fun saveToken(token: String){
        _token = token
    }

    fun getToken(): String?{
        return _token
    }

}