package com.ihfazh.dailytrackerchild.pages.photo_picker

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ihfazh.dailytrackerchild.pages.task_list.createImageFile
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

@Composable
fun CameraView(modifier: Modifier = Modifier, onPhotoSelected: (File) -> Unit = {}){
    val cameraState = rememberCameraState()
    val camSelector by rememberCamSelector(CamSelector.Back)

    // todo move me ok!!
    val context = LocalContext.current
    val file = remember {
        mutableStateOf(
            context.createImageFile()
        )
    }

    val state = remember{
        mutableStateOf("ambilFoto")
    }

    if (state.value == "ambilFoto"){
        CameraPreview(cameraState= cameraState, camSelector = camSelector, modifier = modifier){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 32.dp)

            ) {
                Button(onClick = {

                    cameraState.takePicture(file.value){
                        state.value = "Hello world"
                        Log.d("HAHAH", "CameraView: ${it}")
                    }

                }) {
                    Text(text = "Ambil Foto", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    } else {
        Box(modifier = modifier.fillMaxSize()){
            AsyncImage(
                file.value,
                contentDescription = "Hello world",
                modifier = Modifier.fillMaxSize()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 32.dp)

            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    Button(onClick = {
                        state.value = "ambilFoto"

                    }) {
                        Text(text = "Ambil Foto Lagi", style = MaterialTheme.typography.titleLarge)
                    }

                    Button(onClick = {
                        onPhotoSelected.invoke(file.value)
                    }) {
                       Text(text = "Kirim Foto Ini",  style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }


}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NeedPermission(permissionState: PermissionState, modifier: Modifier = Modifier){

    Column(
        modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            permissionState.launchPermissionRequest()
        }) {
            Text(
                "Minta Ijin Kamera",
                color = Color.White
            )
        }

    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(modifier: Modifier = Modifier, onPhotoSelected: (File) -> Unit = {}){
    val permission = rememberPermissionState(permission = Manifest.permission.CAMERA)

    if (permission.status.isGranted){
        CameraView(modifier = modifier, onPhotoSelected)
        // display camera directly
    } else {
        NeedPermission(permission, modifier)
    }
}


@Composable
@Preview
fun CameraScreenPreview(){
    CameraScreen()

}