package com.ihfazh.dailytrackerchild

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ihfazh.dailytrackerchild.pages.child_picker.ChildPicker
import com.ihfazh.dailytrackerchild.pages.child_picker.Loading
import com.ihfazh.dailytrackerchild.pages.login.IdleLoginState
import com.ihfazh.dailytrackerchild.pages.login.Login
import com.ihfazh.dailytrackerchild.pages.login.Submitting
import com.ihfazh.dailytrackerchild.ui.theme.DailyTrackerChildTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTrackerChildTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                    ,
                    color = MaterialTheme.colorScheme.background
                ) {
//                    TaskList()
                    ChildPicker(Loading)
//                    Login(("Apa kabar ini error"))
                }
            }
        }
    }
}