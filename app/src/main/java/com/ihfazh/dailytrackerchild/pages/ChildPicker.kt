package com.ihfazh.dailytrackerchild.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.components.ProfileCard
import com.ihfazh.dailytrackerchild.components.ProfileItem


@Composable
fun ChildPicker(){

    val profiles = listOf<ProfileItem>(
        ProfileItem("","Sakinah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.4F),
        ProfileItem("","Fukaihah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.5F),
        ProfileItem("", "Khoulah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.9F),
        ProfileItem("","Mimi", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.1F),
        ProfileItem("","Isa", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.4F),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {

        Text(
            text = "Kamu Siapa?",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Pilih siapa yang akan menyelesaikan tugas",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(40.dp))


        LazyRow {
            items(profiles){profile ->
                ProfileCard(
                    profile = profile,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }

    }

}


@Preview(widthDp = 1280, heightDp = 800)
@Composable
fun ChildPickerPreview(){
    ChildPicker()
}
