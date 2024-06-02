package com.ihfazh.dailytrackerchild.pages.task_list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit,
    onTitleClicked: (title: String) -> Unit
){

    val state = viewModel.state.collectAsState()

    TaskList(
        state = state.value,
        modifier = modifier.padding(32.dp),
        onRetryClicked = {viewModel.getTaskList()},
        onTaskFinish = {viewModel.markTaskAsFinished(it.id)},
        onProfileClicked = onProfileClicked,
        onTitleClick = onTitleClicked
    )
}