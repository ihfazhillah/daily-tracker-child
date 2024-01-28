package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihfazh.dailytrackerchild.R


enum class TaskStatus {Todo, Pending, Finished, Processing, Error}


data class Task(
    val id: String,
    val title : String,
    val status: TaskStatus
)

typealias onTaskFinish = (id: String) -> Unit


@Composable
fun TaskCard(
    task: Task,
    modifier: Modifier = Modifier,
    onTaskFinishClick: onTaskFinish = {},
){
    val statusColor = when(task.status){
        TaskStatus.Todo -> Color.Red
        TaskStatus.Pending -> Color.Yellow
        TaskStatus.Finished -> Color.Green
        TaskStatus.Processing -> Color.Gray
        TaskStatus.Error -> Color.Red
    }

    val iconResourceId = when(task.status){
        TaskStatus.Todo -> Icons.Default.FavoriteBorder
        TaskStatus.Pending -> Icons.Default.Lock
        TaskStatus.Finished -> Icons.Default.Favorite
        TaskStatus.Processing -> Icons.Default.Info
        TaskStatus.Error -> Icons.Default.Clear
    }

    Card (
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp, 10.dp)
    ){

        Text(
            text = task.title,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
        Divider(thickness=1.dp)


        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ){
            
            Box(modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(statusColor)
                .padding(8.dp)

            ){
                Icon(
                    iconResourceId,
                    contentDescription = "Sudah Selesai",
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

            if ((task.status == TaskStatus.Todo) or (task.status == TaskStatus.Error)){
                ElevatedButton(
                    onClick = {onTaskFinishClick.invoke(task.id)},
                    modifier = Modifier

                ) {
                    Text(text = "Selesaikan")
                }
            }
        }
    }
}


@Preview
@Composable
fun TaskCardPreview(){
    val tasks = listOf<Task>(
        Task("1", "Sholat Subuh pada waktunya", TaskStatus.Finished),
        Task("2", "Mengerjakan PR Ustadz", TaskStatus.Pending),
        Task("3", "Dot Isa pagi", TaskStatus.Error),
        Task("3", "Dot Isa pagi", TaskStatus.Processing),
        Task("3", "Dot Isa pagi", TaskStatus.Todo),
    )

    Column(
    ){
        tasks.map{task ->
            TaskCard(
                task,
                Modifier.padding(12.dp, 24.dp)
            )
        }
    }

}