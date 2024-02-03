package com.ihfazh.dailytrackerchild

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavOptionsDsl
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ihfazh.dailytrackerchild.pages.child_picker.ChildPicker
import com.ihfazh.dailytrackerchild.pages.child_picker.Loading
import com.ihfazh.dailytrackerchild.pages.login.LoginScreen
import com.ihfazh.dailytrackerchild.pages.login.LoginViewModel

@Composable
fun DailyTrackerComposeChildApp(){
    val navController = rememberNavController()
    DailyTrackerNavHost(navController = navController)
}


@Composable
fun DailyTrackerNavHost(
    navController: NavHostController
){

    val activity = (LocalContext.current as MainActivity)

    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(
                viewModel = viewModel(factory = LoginViewModel.Factory) ,
                onLoginSuccess = {
                    navController.navigate("child-picker" ){
                        popUpTo("login"){
                            inclusive = true
                        }
                    }
                }
            )
        }
        
        composable("child-picker"){
            ChildPicker(state = Loading)
        }

    }

}