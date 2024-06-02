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
import com.ihfazh.dailytrackerchild.ui.theme.DailyTrackerChildTheme

class MainActivity : ComponentActivity() {

    private val tokenCacheUtil
        get() = (application as DailyTrackerChildApplication).compositionRoot.tokenCacheUtil


    override fun onSaveInstanceState(outState: Bundle) {
        val token = tokenCacheUtil.getToken()
        outState.putString("Token", token)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.run {
            val token = getString("Token")
            if (token != null){
                tokenCacheUtil.saveToken(token)
            }
        }

        setContent {
            DailyTrackerChildTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
//                        .padding(24.dp)
                    ,
                    color = MaterialTheme.colorScheme.background
                ) {

                    DailyTrackerComposeChildApp()


                }
            }
        }
    }
}