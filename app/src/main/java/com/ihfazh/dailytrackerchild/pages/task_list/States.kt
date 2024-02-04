package com.ihfazh.dailytrackerchild.pages.task_list

import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.components.Task
import com.ihfazh.dailytrackerchild.components.TaskStatus

sealed interface TaskListStates

open class BaseState(open val profile: ProfileItem): TaskListStates
data class Loading(override val profile: ProfileItem): BaseState(profile)
data class Idle(override val profile: ProfileItem, val tasks: List<Task>): BaseState(profile){
    fun updateTaskStatusById(id: String, status: TaskStatus) = copy(profile, tasks = tasks.map{task ->
        if (task.id == id) {
            task.copy(status = status)
        } else {
            task
        }
    })
}
data class Error(override val profile: ProfileItem, val error: String): BaseState(profile)
