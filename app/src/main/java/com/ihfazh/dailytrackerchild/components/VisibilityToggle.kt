package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.R

@Composable
fun VisibilityToggle(visible: Boolean, onToggle: () -> Unit){

    val text = if (visible){
        "Sembunyikan Task Selesai"
    } else {
        "Tampilkan Task Selesai"
    }

    val resourceId = if (visible){
        R.drawable.eye_slash
    } else {
        R.drawable.eye
    }

    OutlinedButton(onClick = onToggle) {

        Icon(
            painter = painterResource(id = resourceId),
            contentDescription = "EYE",
            modifier = Modifier.width(16.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(text = text)

    }

}


@Preview
@Composable
fun VisibilityTogglePreview(){
    val visible = remember {
        mutableStateOf(true)
    }

    VisibilityToggle(visible.value) { visible.value = !visible.value }

}