package com.example.aubreymercado_comp304lab1_ex1.aubreymercado_comp304lab2_ex1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)

class TaskViewModel : ViewModel() {
    private val _taskListState = MutableStateFlow<List<Task>>(emptyList())
    val taskListState: StateFlow<List<Task>> = _taskListState

    fun addTask(task: Task) {
        _taskListState.update { currentList -> currentList + task }
    }

    fun updateTask(updatedTask: Task) {
        _taskListState.update { currentList ->
            currentList.map { task ->
                if (task.id == updatedTask.id) updatedTask else task
            }
        }
    }

    fun removeTask(taskId: String) {
        _taskListState.update { currentList ->
            currentList.filter { it.id != taskId }
        }
    }

    fun refreshTaskList() {
        _taskListState.value = _taskListState.value
    }

    fun getTaskById(taskId: String): Task? {
        return _taskListState.value.find { it.id == taskId }
    }
}
