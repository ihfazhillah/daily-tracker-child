package com.ihfazh.dailytrackerchild.remote

import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.components.Task
import com.ihfazh.dailytrackerchild.components.TaskStatus
import com.ihfazh.dailytrackerchild.fp.Outcome
import com.ihfazh.dailytrackerchild.fp.OutcomeError
import com.ihfazh.dailytrackerchild.fp.Success
import kotlinx.coroutines.delay


data class LoginBody(val username: String, val password: String)
data class LoginResponse(val token: String)

data class ChildrenResponse(val profiles: List<ProfileItem>)

data class TaskListResponse(val tasks: List<Task>)

class DummyClient {
    val tasks = listOf<Task>(
        Task("1", "Sholat Subuh", TaskStatus.Finished),
        Task("2", "Mengerjakan PR Ustadz", TaskStatus.Pending),
        Task("3", "Dot ISA Pagi", TaskStatus.Todo),
        Task("4", "Belajar Sama Amah Arini", TaskStatus.Todo),
        Task("5", "Belajar Sama Amah Rufa", TaskStatus.Todo),
        Task("6", "Dot Isa Sore", TaskStatus.Todo),
        Task("6", "Trampolin 100 kali", TaskStatus.Todo),
        Task("6", "Sapu sapu rumah", TaskStatus.Todo),
        Task("6", "Sepedaan", TaskStatus.Todo),
    )
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

    suspend fun getTaskList(id: String): Outcome<TaskListResponse, OutcomeError> {
        delay(1000)
        return Outcome.success(TaskListResponse(tasks))

        TODO("Not yet implemented")
    }

    suspend fun markTaskAsFinished(id: String): Outcome<Task, OutcomeError> {
        delay(1000)

        val statuses = listOf(TaskStatus.Finished, TaskStatus.Pending)
        val task = tasks.find { t -> t.id == id } ?: return Outcome.failure(OutcomeError("Task not found"))

        return Outcome.success(task.copy(status = statuses.random()))
    }

}