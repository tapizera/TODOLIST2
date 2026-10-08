package com.example.todolist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.viewmodel.TarefaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLista(
    viewModel: TarefaViewModel,
    onAdicionar: () -> Unit,
    onAbrirDetalhes: (Int) -> Unit
) {
    val tarefas by viewModel.tarefas.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Minhas Tarefas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdicionar) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar tarefa")
            }
        }
    ) { padding ->
        if (tarefas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhuma tarefa ainda. Toque no + para adicionar!")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tarefas, key = { it.id }) { tarefa ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onAbrirDetalhes(tarefa.id) }
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = tarefa.concluida,
                                onCheckedChange = {
                                    viewModel.atualizar(tarefa.copy(concluida = it))
                                }
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(tarefa.titulo, style = MaterialTheme.typography.titleMedium)
                                if (tarefa.descricao.isNotBlank()) {
                                    Text(tarefa.descricao, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                            IconButton(onClick = { viewModel.deletar(tarefa) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Excluir")
                            }
                        }
                    }
                }
            }
        }
    }
}