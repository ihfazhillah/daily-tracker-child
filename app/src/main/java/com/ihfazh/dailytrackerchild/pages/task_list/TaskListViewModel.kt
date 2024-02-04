package com.ihfazh.dailytrackerchild.pages.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ihfazh.dailytrackerchild.DailyTrackerChildApplication
import com.ihfazh.dailytrackerchild.components.ProfileItem
import com.ihfazh.dailytrackerchild.components.TaskStatus
import com.ihfazh.dailytrackerchild.fp.Failure
import com.ihfazh.dailytrackerchild.fp.Success
import com.ihfazh.dailytrackerchild.remote.DummyClient
import com.ihfazh.dailytrackerchild.utils.DateProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val client: DummyClient,
    private val profileItem: ProfileItem,
    dateProvider: DateProvider
): ViewModel(){

    private val date = dateProvider.getDateItem()

    private val _state = MutableStateFlow<BaseState>(Loading(profileItem, date))
    val state = _state.asStateFlow()


    fun getTaskList(){
        viewModelScope.launch(Dispatchers.IO){
            val response = client.getTaskList(profileItem.id)
            _state.value = when(response){
                is Failure -> Error(profileItem, date, response.error.msg)
                is Success -> Idle(profileItem, date, response.value.tasks)
            }
        }

    }

    fun markTaskAsFinished(id: String){
        if (state.value !is Idle){
            return
        }


        viewModelScope.launch(Dispatchers.IO) {
            _state.value = (state.value as Idle).updateTaskStatusById(id, TaskStatus.Processing)

            val response = client.markTaskAsFinished(id)

            _state.value = when(response){
                is Failure -> (state.value as Idle).updateTaskStatusById(id, TaskStatus.Error)
                is Success -> (state.value as Idle).updateTaskStatusById(response.value.id, response.value.status)
            }
        }
    }

    init {
        getTaskList()
    }

    companion object{
        fun Factory(profileItem: ProfileItem): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as DailyTrackerChildApplication
                return TaskListViewModel(
                    application.compositionRoot.client,
                    profileItem,
                    application.compositionRoot.dateProvider
                ) as T
            }
        }
    }
}



