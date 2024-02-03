package com.ihfazh.dailytrackerchild.remote

import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.fp.Outcome
import com.ihfazh.dailytrackerchild.fp.OutcomeError
import com.ihfazh.dailytrackerchild.fp.Success
import kotlinx.coroutines.delay


data class LoginBody(val username: String, val password: String)
data class LoginResponse(val token: String)

data class ChildrenResponse(val profiles: List<ProfileItem>)

class DummyClient {
    suspend fun login(body: LoginBody): Outcome<LoginResponse, OutcomeError>{
        delay(1000)
//        return Outcome.failure(OutcomeError("hello"))
        return Success(LoginResponse("1234"))
    }

    suspend fun getChildren(): Outcome<ChildrenResponse, OutcomeError>{
        delay(1000)
        val profiles = listOf<ProfileItem>(
            ProfileItem("1","Sakinah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.4F),
            ProfileItem("2","Fukaihah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.5F),
            ProfileItem("3", "Khoulah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.9F),
            ProfileItem("4","Mimi", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.1F),
            ProfileItem("5","Isa", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.4F),
        )

//        return Outcome.failure(OutcomeError("Hahahaha"))
        return Outcome.success(ChildrenResponse(profiles))
    }

}