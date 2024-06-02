package com.ihfazh.dailytrackerchild.pages.task_list

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.pages.photo_picker.CameraScreen
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}


@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit,
    onTitleClicked: (title: String) -> Unit,
){

    val state = viewModel.state.collectAsState()

    if (state.value is PickPhoto){
        CameraScreen(modifier) {
            viewModel.markTaskAsFinished((state.value as PickPhoto).selectedTask.id, it)
        }
    } else {
        TaskList(
            state = state.value,
            modifier = modifier.padding(32.dp),
            onRetryClicked = { viewModel.getTaskList() },
            onTaskFinish = { task ->
                if (task.need_verification) {
                    viewModel.pickPhotoFor(task)
                } else {
                    viewModel.markTaskAsFinished(task.id)
                }
            },
            onProfileClicked = onProfileClicked,
            onTitleClick = onTitleClicked
        )
    }
}

