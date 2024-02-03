package com.ihfazh.dailytrackerchild.pages.child_picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ihfazh.dailytrackerchild.components.OnProfileClicked


@Composable
fun ChildPickerScreen(
    viewModel: ChildPickerViewModel,
    modifier: Modifier = Modifier,
    onProfileClicked: OnProfileClicked
){

    val state = viewModel.state.collectAsState()

    ChildPicker(
        state=state.value,
        modifier=modifier,
        onRetryClicked = {viewModel.getChildren()},
        onChildClicked = onProfileClicked
    )

}
