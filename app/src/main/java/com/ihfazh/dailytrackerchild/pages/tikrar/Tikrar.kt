package com.ihfazh.dailytrackerchild.pages.tikrar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Mukarror(
    val text: String,

    // this should relative, we will
    // uri-ify using tikrar path config later
    val audioPath: String
)

data class TikrarData(
    val mukrarorList: List<Mukarror>,
    val isAudioPlaying: Boolean,
    val activeIndex: Int,
    val tikrarTimes: Int,
    val maxTikrar: Int
)


@Composable
fun Tikrar(
    data: TikrarData,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
){
    Column (modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(32.dp, 0.dp)
            ,
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Dengar dan Ulangi",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight =MaterialTheme.typography.headlineMedium.fontWeight,
            )
        }
        
        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f, fill = true)
                .padding(32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = data.mukrarorList[data.activeIndex].text,
                fontSize = MaterialTheme.typography.displayLarge.fontSize,
                fontWeight =MaterialTheme.typography.displayLarge.fontWeight,
            )
        }

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            for (i in 0..data.maxTikrar - 1){
                val backgroundColor = if (i > data.tikrarTimes)  Color.DarkGray else Color.Green
               Box(
                   modifier
                       .size(32.dp)
                       .clip(CircleShape)
                       .background(backgroundColor)
               )

                if (i < data.maxTikrar - 1){
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp , 16.dp, 0.dp, 32.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                enabled = !data.isAudioPlaying,
                onClick = onNext) {
                Text(text = "Lanjutkan")
                Icon(Icons.Default.ArrowForward, contentDescription = null)

            }
        }
    }
}


@Preview(device = "spec:parent=Nexus 7 2013,orientation=landscape")
@Composable
fun TikrarPreview(){
    val tikrarData = TikrarData(
        listOf(Mukarror("التَّحِيَّاتُ لله", "hello world")),
        true,
        0,
        0,
        5
    )
    Tikrar(tikrarData)
}
