package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class TarefaRepository(private val dao: TarefaDao) {
    val tarefas: Flow<List<Tarefa>> = dao.listar()

    suspend fun buscarPorId(id: Int) = dao.buscarPorId(id)
    suspend fun inserir(tarefa: Tarefa) = dao.inserir(tarefa)
    suspend fun atualizar(tarefa: Tarefa) = dao.atualizar(tarefa)
    suspend fun deletar(tarefa: Tarefa) = dao.deletar(tarefa)
}