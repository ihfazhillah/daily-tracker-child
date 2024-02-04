package com.ihfazh.dailytrackerchild.pages.task_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ihfazh.dailytrackerchild.components.ProfileItem

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    modifier: Modifier = Modifier
){

    val state = viewModel.state.collectAsState()

    TaskList(
        state = state.value,
        modifier = modifier,
        onRetryClicked = {viewModel.getTaskList()},
        onTaskFinish = {viewModel.markTaskAsFinished(it)}
    )
}