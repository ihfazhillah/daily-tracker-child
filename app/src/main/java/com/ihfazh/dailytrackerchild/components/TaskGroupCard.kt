package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.data.Task
import com.ihfazh.dailytrackerchild.data.TaskGroup
import com.ihfazh.dailytrackerchild.data.TaskStatus

@Composable
fun TaskGroupCard(
    taskGroup: TaskGroup,
    modifier: Modifier = Modifier,
    onSelesai: OnSelesai = {},
    onUdzur: OnUdzur = {},
){

    Column(
        modifier= modifier
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .border(1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
        ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // full width

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                // box for the time text

                Text(
                    text = taskGroup.time,
                    style=MaterialTheme.typography.titleMedium,
                    color = Color.White

                )
            }

        }

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            taskGroup.tasks.map{ task ->
                TaskItem(task = task, onSelesai = onSelesai, onUdzur = onUdzur)
            }
        }

    }

}


@Preview
@Composable
fun TaskGroupPreview(){
    val taskGroup = TaskGroup(
        "04:00",
        listOf(
            Task("1", "Sholat Subuh", TaskStatus.todo, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"),
            Task("2", "Dzikir Pagi", TaskStatus.pending, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"),
            Task("3", "Murojaah Al Quran", TaskStatus.finished, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png"),
            Task("4", "PR Khot", TaskStatus.udzur, "https://cms.ksatriamuslim.com/media/sholat_rL2kmdq.png", "Sedang pusing sekali.")
        )
    )

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        TaskGroupCard(taskGroup = taskGroup)
    }

}