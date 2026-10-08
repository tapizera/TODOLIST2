package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.Tarefa
import com.example.todolist.data.TarefaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TarefaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TarefaRepository(
        AppDatabase.Companion.getDatabase(application).tarefaDao()
    )

    val tarefas: StateFlow<List<Tarefa>> = repository.tarefas
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    suspend fun buscarPorId(id: Int) = repository.buscarPorId(id)

    fun inserir(titulo: String, descricao: String) {
        viewModelScope.launch {
            repository.inserir(Tarefa(titulo = titulo, descricao = descricao))
        }
    }

    fun atualizar(tarefa: Tarefa) {
        viewModelScope.launch { repository.atualizar(tarefa) }
    }

    fun deletar(tarefa: Tarefa) {
        viewModelScope.launch { repository.deletar(tarefa) }
    }
}