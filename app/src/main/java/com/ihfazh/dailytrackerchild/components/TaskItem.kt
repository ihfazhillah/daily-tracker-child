package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


typealias OnSelesai = (Task) -> Unit
typealias OnUdzur = (Task) -> Unit

@Composable
fun TaskItem(task: Task, modifier: Modifier = Modifier, onSelesai: OnSelesai = {}, onUdzur: OnUdzur = {}){
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
                textDecoration = textDecoration
            )

            if (task.status == TaskStatus.todo){

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                    Button(onClick = {
                        onSelesai.invoke(task)
                    }) {
                        Icon(Icons.Default.Check, "Done")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Selesai")
                    }

                    OutlinedButton(onClick = {
                        onUdzur.invoke(task)
                    }) {
                        Icon(Icons.Default.Warning, "Udzur")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Udzur")
                    }
                }

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
                        .background(Color(204, 514, 0))
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


                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                        Button(onClick = {
                            onSelesai.invoke(task)
                        }) {
                            Icon(Icons.Default.Check, "Done")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Selesai")
                        }

                        OutlinedButton(onClick = {
                            onUdzur.invoke(task)
                        }) {
                            Icon(Icons.Default.Warning, "Udzur")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Udzur")
                        }
                    }
                }

            }

        }


    }
}


@Preview(device = "id:pixel_3")
@Composable
fun TaskItemPreview(){
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TaskItem(task = Task("1", "Sholat Subuh", TaskStatus.todo, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"))
        TaskItem(task = Task("2", "Dzikir Pagi", TaskStatus.pending, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"))
        TaskItem(task = Task("3", "Murojaah Al Quran", TaskStatus.finished, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"))
        TaskItem(task = Task("4", "PR Khot", TaskStatus.udzur, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png", "Sedang pusing sekali."))
        TaskItem(task = Task("6", "Sarapan", TaskStatus.processing, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png" ))
        TaskItem(task = Task("6", "Error", TaskStatus.error, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png" ))
    }
}