package com.ihfazh.dailytrackerchild.pages.child_picker

import com.ihfazh.dailytrackerchild.components.ProfileItem

sealed interface ChildState

data object Loading: ChildState

data class Error(val error: String): ChildState
data class Idle(val profiles: List<ProfileItem>): ChildState