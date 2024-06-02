package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ihfazh.dailytrackerchild.R
import com.ihfazh.dailytrackerchild.data.Task
import com.ihfazh.dailytrackerchild.data.TaskStatus


typealias OnSelesai = (Task) -> Unit
typealias OnUdzur = (Task, String) -> Unit
typealias OnTitleClick = (Task) -> Unit

@Composable
fun TaskItem(task: Task, modifier: Modifier = Modifier, onSelesai: OnSelesai = {}, onUdzur: OnUdzur = {_, _ ->}, onTitleClick: OnTitleClick = {}){
    Row(
        modifier=modifier
            .padding(8.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            model = task.image, contentDescription = task.title,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            
            val textDecoration = if (task.status == TaskStatus.finished || task.status == TaskStatus.udzur){
                TextDecoration.LineThrough
            } else {null}

            Text(
                text = task.title,
                style = MaterialTheme.typography.titleLarge,
                textDecoration = textDecoration,
                modifier = Modifier.clickable {
                    onTitleClick.invoke(task)
                }
            )

            if (task.status == TaskStatus.todo){

                TaskAction(onSelesai, task, onUdzur)

            }

            if (task.status == TaskStatus.pending){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.DarkGray)
                        .padding(8.dp),

                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(

                        Icons.Default.ThumbUp, "Thumb Up",
                        tint = Color.White


                    )
                    Text("Menunggu Persetujuan", color = Color.White)
                }
            }

            if(task.status == TaskStatus.finished){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0, 204, 51))
                        .padding(8.dp),

                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(

                        Icons.Default.CheckCircle, "Selesai",
                        tint = Color.White


                    )
                    Text("Sudah Selesai. Good Job!!", color = Color.White)
                }

            }

            if(task.status == TaskStatus.udzur){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(204, 54, 0))
                        .padding(8.dp),

                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(

                        Icons.Default.Warning, "Warning",
                        tint = Color.White


                    )

                    if (task.udzur != null){
                        Text(task.udzur, color = Color.White)
                    }
                }

            }

            if(task.status == TaskStatus.processing){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .padding(8.dp),

                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CircularProgressIndicator(modifier = Modifier
                        .width(30.dp)
                        .height(30.dp))

                    Text("Sedang memproses")

                }

            }

            if(task.status == TaskStatus.error){

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.Red)
                            .padding(8.dp),

                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(Icons.Default.Close, "Error", tint = Color.White)

                        Text("Error, Coba Lagi...", color = Color.White)

                    }


                    TaskAction(onSelesai, task, onUdzur)
                }

            }

        }


    }
}

@Composable
private fun TaskAction(
    onSelesai: OnSelesai,
    task: Task,
    onUdzur: OnUdzur
) {

    val udzurReasonInputDisplay = remember {
        mutableStateOf(false)
    }
    val udzurReasonValue = remember {
        mutableStateOf("")
    }


    if (udzurReasonInputDisplay.value){
        Column {
            TextField(value = udzurReasonValue.value, onValueChange = {
                udzurReasonValue.value = it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ,
                placeholder = {
                              Text(text = "Tuliskan alasan udzur kamu")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onUdzur.invoke(task, udzurReasonValue.value)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.send), contentDescription = "send")
                    }

                }
            )

        }



    } else {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                onSelesai.invoke(task)
            }) {
                Icon(Icons.Default.Check, "Done")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Selesai")
            }

            OutlinedButton(onClick = {
                udzurReasonInputDisplay.value = true
            }) {
                Icon(Icons.Default.Warning, "Udzur")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Udzur")
            }
        }

    }

}


@Preview(device = "id:pixel_3")
@Composable
fun TaskItemPreview(){
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TaskItem(task = Task("1", "Sholat Subuh", "100", TaskStatus.todo, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"))
//        TaskItem(task = Task("2", "Dzikir Pagi", TaskStatus.pending, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"))
//        TaskItem(task = Task("3", "Murojaah Al Quran", TaskStatus.finished, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"))
        TaskItem(task = Task("4", "PR Khot", "1", TaskStatus.udzur, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png", "Sedang pusing sekali."))
//        TaskItem(task = Task("6", "Sarapan", TaskStatus.processing, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png" ))
//        TaskItem(task = Task("6", "Error", TaskStatus.error, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png" ))
    }
}