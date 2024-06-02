package com.ihfazh.dailytrackerchild.data

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val title : String,
    val time: String,
    val status: TaskStatus,
    val image: String? = null,
    val udzur: String? = null,
    val need_verification: Boolean = false,
)