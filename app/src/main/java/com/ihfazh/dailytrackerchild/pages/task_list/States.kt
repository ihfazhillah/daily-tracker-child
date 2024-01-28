package com.ihfazh.dailytrackerchild.pages.task_list

import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.components.Task

sealed interface TaskListStates

open class BaseState(open val profile: ProfileItem): TaskListStates
data class Loading(override val profile: ProfileItem): BaseState(profile)
data class Idle(override val profile: ProfileItem, val tasks: List<Task>): BaseState(profile)
data class Error(override val profile: ProfileItem, val error: String): BaseState(profile)
