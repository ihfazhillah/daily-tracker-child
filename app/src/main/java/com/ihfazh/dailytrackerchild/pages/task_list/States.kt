package com.ihfazh.dailytrackerchild.pages.task_list

import com.ihfazh.dailytrackerchild.components.DateItem
import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.data.Task
import com.ihfazh.dailytrackerchild.data.TaskStatus

sealed interface TaskListStates

open class BaseState(open val profile: ProfileItem, open val dateItem: DateItem): TaskListStates
data class Loading(override val profile: ProfileItem, override val dateItem: DateItem): BaseState(profile, dateItem)
data class Idle(override val profile: ProfileItem, override val dateItem: DateItem, val tasks: List<Task>): BaseState(profile, dateItem){
    fun updateTaskStatusById(id: String, status: TaskStatus) = copy(tasks = tasks.map{ task ->
        if (task.id == id) {
            task.copy(status = status)
        } else {
            task
        }
    })
}
data class Error(override val profile: ProfileItem, override val dateItem: DateItem, val error: String): BaseState(profile, dateItem)
data class PickPhoto(
    override val profile: ProfileItem,
    override val dateItem: DateItem,
    val tasks: List<Task>,
    val selectedTask: Task
): BaseState(profile, dateItem)

