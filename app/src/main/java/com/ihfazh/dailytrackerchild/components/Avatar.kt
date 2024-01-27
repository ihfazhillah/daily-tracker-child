package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


data class Child(
    val avatarUrl: String,
    val name: String
)

@Composable
fun Avatar(child: Child){
    AsyncImage(
        model = child.avatarUrl,
        contentDescription = null,
        modifier = Modifier
            .height(80.dp)
            .clip(CircleShape)
    )
}




@Preview
@Composable
fun AvatarPreview(){
    Avatar(Child("https://ptetutorials.com/images/user-profile.png", "Sakinah"))
}
