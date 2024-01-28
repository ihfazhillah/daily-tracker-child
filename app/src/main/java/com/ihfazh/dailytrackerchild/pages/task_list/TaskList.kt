package com.ihfazh.dailytrackerchild.pages.task_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.components.Avatar
import com.ihfazh.dailytrackerchild.components.Child
import com.ihfazh.dailytrackerchild.components.Date
import com.ihfazh.dailytrackerchild.components.DateItem
import com.ihfazh.dailytrackerchild.components.HijriDateItem
import com.ihfazh.dailytrackerchild.components.MyProgress
import com.ihfazh.dailytrackerchild.components.Task
import com.ihfazh.dailytrackerchild.components.TaskCard
import com.ihfazh.dailytrackerchild.components.TaskStatus

@Composable
fun TaskList(){
    val tasks = listOf<Task>(
        Task("1", "Sholat Subuh", TaskStatus.Finished),
        Task("2", "Mengerjakan PR Ustadz", TaskStatus.Pending),
        Task("3", "Dot ISA Pagi", TaskStatus.Todo),
        Task("4", "Belajar Sama Amah Arini", TaskStatus.Todo),
        Task("5", "Belajar Sama Amah Rufa", TaskStatus.Todo),
        Task("6", "Dot Isa Sore", TaskStatus.Todo),
        Task("6", "Trampolin 100 kali", TaskStatus.Todo),
        Task("6", "Sapu sapu rumah", TaskStatus.Todo),
        Task("6", "Sepedaan", TaskStatus.Todo),
    )

    val user = Child("https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", "Isa")

    val date = DateItem(
        HijriDateItem(15, "Rajab", 1445),
        "27 Januari 2024"
    )


    Surface {
        Column (
            modifier = Modifier
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Avatar(child = user)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Row {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(24.dp)
                ){

                    Date(
                        item = date,
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    MyProgress(progress = tasks.filter {it.status === TaskStatus.Finished}.size / tasks.size.toFloat())
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp)
                ){
                    items(tasks){task ->
                        TaskCard(
                            task = task,
                            modifier = Modifier
                                .padding(12.dp)
                        )
                    }
                }
            }
        }

    }
}


@Preview(widthDp = 1280)
@Composable
fun TaskListPreview(){
    TaskList()
}