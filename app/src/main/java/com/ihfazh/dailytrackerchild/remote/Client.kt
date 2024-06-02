package com.ihfazh.dailytrackerchild.remote

import com.ihfazh.dailytrackerchild.data.Task
import com.ihfazh.dailytrackerchild.fp.Outcome
import com.ihfazh.dailytrackerchild.fp.OutcomeError
import java.io.File

interface Client {
    suspend fun login(body: LoginBody): Outcome<LoginResponse, OutcomeError>
    suspend fun getChildren(): Outcome<ChildrenResponse, OutcomeError>
    suspend fun getTaskList(id: String): Outcome<TaskListResponse, OutcomeError>

    suspend fun markTaskAsFinished(id: String): Outcome<Task, OutcomeError>
    suspend fun markTaskAsFinished(id: String, file: File): Outcome<Task, OutcomeError>
}