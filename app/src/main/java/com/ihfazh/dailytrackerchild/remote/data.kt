package com.ihfazh.dailytrackerchild.remote

import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.components.Task
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(val username: String, val password: String)
@Serializable
data class LoginResponse(val token: String)
@Serializable
data class ChildrenResponse(val profiles: List<ProfileItem>)

@Serializable
data class TaskRemote(
    val id: String,
    val title : String,
    val status: String,
    val image: String? = null
)

@Serializable
data class TaskListResponse(val tasks: List<Task>)

@Serializable
data class TaskListFromRemoteResponse(val tasks: List<TaskRemote>)


@Serializable
data class MarkAsFinishedBody(val task_id: String)

@Serializable
data class MarkAsFinishedResponse(val task: TaskRemote)
