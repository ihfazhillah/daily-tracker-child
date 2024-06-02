package com.ihfazh.dailytrackerchild.pages.child_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.types.OnRetryClicked
import com.ihfazh.dailytrackerchild.components.ErrorMessage
import com.ihfazh.dailytrackerchild.components.OnProfileClicked
import com.ihfazh.dailytrackerchild.components.ProfileCard
import com.ihfazh.dailytrackerchild.components.ProfileItem


@Composable
fun ChildPicker(state: ChildState, modifier: Modifier = Modifier, onChildClicked: OnProfileClicked = {}, onRetryClicked: OnRetryClicked = {}){


    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),

        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)


    ) {

        Column {
            Text(
                text = "Kamu Siapa?",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "Pilih siapa yang akan menyelesaikan tugas",
                style = MaterialTheme.typography.titleLarge
            )
        }




        if (state is Idle){
            LazyColumn {
                items(state.profiles){profile ->
                    ProfileCard(
                        profile = profile,
                        modifier = Modifier
                            .padding(16.dp),
                        onProfileClicked = onChildClicked
                    )
                }
            }
        }

        if (state is Loading){
            CircularProgressIndicator(
                Modifier
                    .height(100.dp)
                    .width(100.dp))
        }

        if (state is Error){
            Row {
                ErrorMessage(errorMessage = state.error)

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = onRetryClicked) {
                    Icon( Icons.Rounded.Refresh, contentDescription = null)
                    Text(text = "Retry")
                }

            }
        }



    }

}


@Preview(device = "id:pixel")
@Composable
fun ChildPickerPreview(){
    val profiles = listOf<ProfileItem>(
        ProfileItem("","Sakinah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.4F),
        ProfileItem("","Fukaihah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.5F),
        ProfileItem("", "Khoulah", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.9F),
        ProfileItem("","Mimi", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.1F),
        ProfileItem("","Isa", "https://www.shutterstock.com/image-vector/man-faceless-avatar-260nw-1013409094.jpg", 0.4F),
    )

    ChildPicker(Idle(profiles))
}
