package com.ihfazh.dailytrackerchild.pages.task_list

import android.text.Editable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.ihfazh.dailytrackerchild.ChildrenCache
import com.ihfazh.dailytrackerchild.DailyTrackerChildApplication
import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.pages.login.LoginViewModel
import com.ihfazh.dailytrackerchild.remote.DummyClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskListViewModel(
    private val client: DummyClient,
    private val profileItem: ProfileItem
): ViewModel(){
    private val _profile = MutableStateFlow(ProfileItem.empty())

    private val _state = MutableStateFlow<BaseState>(Loading(profileItem))
    val state = _state.asStateFlow()


    fun getTaskList(){

    }

    fun markTaskAsFinished(id: String){

    }

    companion object{
        fun Factory(profileItem: ProfileItem): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as DailyTrackerChildApplication
                return TaskListViewModel(
                    application.compositionRoot.client,
                    profileItem
                ) as T
            }
        }
    }
}

private fun ProfileItem.Companion.empty() = ProfileItem("-1", "", "", 0f)


