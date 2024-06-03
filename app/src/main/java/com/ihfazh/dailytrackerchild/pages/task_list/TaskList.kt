package com.ihfazh.dailytrackerchild.pages.task_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.ihfazh.dailytrackerchild.components.Avatar
import com.ihfazh.dailytrackerchild.components.Child
import com.ihfazh.dailytrackerchild.components.CircleProgress
import com.ihfazh.dailytrackerchild.components.DateItem
import com.ihfazh.dailytrackerchild.components.HijriDateItem
import com.ihfazh.dailytrackerchild.components.OnSelesai
import com.ihfazh.dailytrackerchild.components.OnUdzur
import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.components.TaskGroupCard
import com.ihfazh.dailytrackerchild.components.VisibilityToggle
import com.ihfazh.dailytrackerchild.components.onTitleClick
import com.ihfazh.dailytrackerchild.data.Task
import com.ihfazh.dailytrackerchild.data.TaskGroup
import com.ihfazh.dailytrackerchild.data.TaskStatus
import com.ihfazh.dailytrackerchild.types.OnRetryClicked


private fun ProfileItem.toChild(): Child = Child(avatarUrl, name)

@Composable
fun TaskList(
    state: BaseState,
    modifier: Modifier = Modifier,
    onTaskFinish: OnSelesai = {},
    onUdzur: OnUdzur = {_, _ ->},
    onRetryClicked: OnRetryClicked = {},
    onProfileClicked: () -> Unit,
    onTitleClick: onTitleClick,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass


){

    val finishedVisible = remember {
        mutableStateOf(true)
    }


    val date = state.dateItem

    val progress = if (state is Idle) {
        state.tasks.filter { it.status in listOf(
            TaskStatus.finished,
            TaskStatus.udzur
        ) }.size.toFloat() / state.tasks.size
    } else {
        state.profile.progress
    }

    val taskGroups: List<TaskGroup> = if (state is Idle){
        state.tasks.filter {
            if (finishedVisible.value){
               true
            } else {
                it.status !in listOf(TaskStatus.finished, TaskStatus.udzur)

            }
        }.groupBy { it.time }
            .map {
                TaskGroup(it.key, it.value)
            }
    } else {
        listOf()
    }



    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column (verticalArrangement = Arrangement.spacedBy(16.dp)){

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT){
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            Avatar(
                                child = state.profile.toChild(),
                                modifier = Modifier.clickable {
                                    onProfileClicked.invoke()
                                }
                            )
                            CircleProgress(progress = progress, diameter = 80.dp)
                        }
                    } else {

                        Avatar(
                            child = state.profile.toChild(),
                            modifier = Modifier.clickable {
                                onProfileClicked.invoke()
                            }
                        )


                    }

                    Text(text = "Assalamualaikum, ${state.profile.name}!", style = MaterialTheme.typography.displaySmall)



                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "${date.hijriDateItem.date} ${date.hijriDateItem.month} ${date.hijriDateItem.year}", style = MaterialTheme.typography.titleLarge)
                    Text(text = date.georgianDateString)
                }




            }

            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM){
                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    CircleProgress(progress = progress)
                }
            }


        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // container

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                horizontalArrangement = Arrangement.End
            ) {
                // toggle button
                VisibilityToggle(visible = finishedVisible.value) {
                    finishedVisible.value = !finishedVisible.value
                }

            }

            if (state is Loading){
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text("Memuat...", style = MaterialTheme.typography.headlineMedium)
                }
            } else if (state is Error){
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: ${state.error}", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.error)
                    OutlinedButton(
                        onClick = onRetryClicked,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)

                    ) {
                        Text(text = "Coba Lagi")
                    }
                }
            } else if (state is Idle) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp, 0.dp, 16.dp, 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    taskGroups.map{
                        TaskGroupCard(
                            taskGroup = it,
                            onTitleClick = {task -> onTitleClick.invoke(task.title)},
                            onSelesai = onTaskFinish,
                            onUdzur = onUdzur
                        )
                    }

                }
            }


        }


    }

}


@Preview(device = "spec:id=reference_desktop,shape=Normal,width=1920,height=1080,unit=dp,dpi=160")
@Composable
fun TaskListPreview(){
    val tasks = listOf<Task>(
        Task("1", "Sholat Subuh", "04:00", TaskStatus.finished),
        Task("2", "Mengerjakan PR Ustadz", "04:00", TaskStatus.pending),
        Task("3", "Dot ISA Pagi", "08:00", TaskStatus.todo),
        Task("4", "Belajar Sama Amah Arini", "10:00", TaskStatus.todo),
        Task("5", "Belajar Sama Amah Rufa", "15:00", TaskStatus.todo),
        Task("6", "Dot Isa Sore", "16:00", TaskStatus.todo),
        Task("6", "Trampolin 100 kali", "16:00", TaskStatus.todo),
        Task("6", "Sapu sapu rumah", "13:00", TaskStatus.todo),
        Task("6", "Sepedaan", "13:00", TaskStatus.todo),
    )
    val date = DateItem(
        HijriDateItem(15, "Rajab", 1445),
        "27 Januari 2024"
    )

    TaskList(
        Idle(
            ProfileItem("1", "Sakinah", "hello", 0.2F),
            date,
            tasks
        ),
        onProfileClicked = {},
        onTitleClick = {}
    )
}