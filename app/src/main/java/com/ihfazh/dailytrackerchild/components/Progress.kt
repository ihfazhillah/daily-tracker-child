package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.ihfazh.dailytrackerchild.ui.theme.Typography
import kotlin.math.roundToInt

@Composable
fun MyProgress(progress: Float){
    var totalProgress = (progress * 100)
    if (totalProgress.isNaN()){
        totalProgress = 0f
    }

    Column {
        Text(text = "Kemajuan Kamu", style = Typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Row (verticalAlignment = Alignment.CenterVertically){
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .height(16.dp)
            )

            Spacer(Modifier.width(16.dp))

            Text(text = "${totalProgress.roundToInt()}%", style = Typography.labelLarge)
        }
    }
}


@Composable
fun CircleProgress(progress: Float, modifier: Modifier = Modifier, diameter: Dp = 100.dp){

    var totalProgress = (progress * 100)
    if (totalProgress.isNaN()){
        totalProgress = 0F
    }

    val fontSize = (0.28f * diameter.value.sp)


    Box(
        modifier = modifier
            ,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            progress = { progress },
            color = MaterialTheme.colorScheme.primary,
            trackColor = Color.LightGray,
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .width(diameter)
                .height(diameter)
        )
        Text(
            text = "${totalProgress.roundToInt()}%",
            fontWeight = FontWeight(800),
            fontSize = fontSize,
            color = MaterialTheme.colorScheme.primary
        )
    }

}


@Preview
@Composable
fun MyProgressPreview(){
    CircleProgress((7.toFloat() / 10), diameter = 100.dp)
}