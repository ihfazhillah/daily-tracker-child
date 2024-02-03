package com.ihfazh.dailytrackerchild.remote

import com.ihfazh.dailytrackerchild.fp.Outcome
import com.ihfazh.dailytrackerchild.fp.OutcomeError
import com.ihfazh.dailytrackerchild.fp.Success
import kotlinx.coroutines.delay


data class LoginBody(val username: String, val password: String)
data class LoginResponse(val token: String)

class DummyClient {
    suspend fun login(body: LoginBody): Outcome<LoginResponse, OutcomeError>{
        delay(1000)
//        return Outcome.failure(OutcomeError("hello"))
        return Success(LoginResponse("1234"))
    }

}