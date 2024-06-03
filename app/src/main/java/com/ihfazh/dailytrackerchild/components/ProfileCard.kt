package com.ihfazh.dailytrackerchild.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

/*
Mainly used for user picker
 */

@Serializable
data class ProfileItem (
    val id: String,
    val name: String,
    val avatarUrl: String,
    val progress: Float,
) {
    companion object
}


typealias OnProfileClicked = (profile: ProfileItem) -> Unit


@Composable
fun ProfileCard(profile: ProfileItem, modifier: Modifier = Modifier, onProfileClicked: OnProfileClicked = {}){
    val child = Child(profile.avatarUrl, profile.name)
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable { onProfileClicked.invoke(profile) }){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Avatar(child, width = 100.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text=profile.name,
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(32.dp))
            MyProgress(progress = profile.progress)
        }
    }

}


@Composable
fun ProfileWithProgress(profile: ProfileItem, modifier: Modifier = Modifier, onProfileClicked: OnProfileClicked = {}){
    val child = Child(profile.avatarUrl, profile.name)

    Column(modifier = modifier
        .clickable { onProfileClicked.invoke(profile) }
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier
            .width(100.dp)
            .height(100.dp)) {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){
                Avatar(child, width = 80.dp)
                CircularProgressIndicator(
                    progress = { profile.progress },
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = Color.LightGray,
                    strokeWidth = 8.dp,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            }
        }
        
        Text(text = child.name, style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onPrimaryContainer, maxLines = 1)
    }
}


@Preview
@Composable
fun ProfileCardPreview(){
    val profile = ProfileItem("hello", "Sakinah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.3F)
    ProfileWithProgress(profile = profile)
}