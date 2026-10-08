package com.example.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefas ORDER BY id DESC")
    fun listar(): Flow<List<Tarefa>>

    @Query("SELECT * FROM tarefas WHERE id = :id")
    suspend fun buscarPorId(id: Int): Tarefa?

    @Insert
    suspend fun inserir(tarefa: Tarefa)

    @Update
    suspend fun atualizar(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)
}