package com.example.todolist.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.Tarefa
import com.example.todolist.viewmodel.TarefaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(
    viewModel: TarefaViewModel,
    tarefaId: Int?,
    onVoltar: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var tarefaAtual by remember { mutableStateOf<Tarefa?>(null) }

    // Se veio um id, carrega a tarefa pra editar
    LaunchedEffect(tarefaId) {
        if (tarefaId != null) {
            viewModel.buscarPorId(tarefaId)?.let {
                tarefaAtual = it
                titulo = it.titulo
                descricao = it.descricao
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (tarefaId == null) "Nova Tarefa" else "Editar Tarefa") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            Button(
                onClick = {
                    val atual = tarefaAtual
                    if (atual == null) {
                        viewModel.inserir(titulo, descricao)
                    } else {
                        viewModel.atualizar(atual.copy(titulo = titulo, descricao = descricao))
                    }
                    onVoltar()
                },
                enabled = titulo.isNotBlank(),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Salvar")
            }
        }
    }
}