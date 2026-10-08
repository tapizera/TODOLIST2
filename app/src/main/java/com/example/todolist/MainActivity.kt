package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.ui.screens.TelaCadastro
import com.example.todolist.ui.screens.TelaLista
import com.example.todolist.ui.theme.TODOLISTTheme
import com.example.todolist.viewmodel.TarefaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOLISTTheme {
                val navController = rememberNavController()
                val viewModel: TarefaViewModel = viewModel()

                NavHost(navController = navController, startDestination = "lista") {

                    composable("lista") {
                        TelaLista(
                            viewModel = viewModel,
                            onAdicionar = { navController.navigate("cadastro") },
                            onAbrirDetalhes = { id -> navController.navigate("cadastro?id=$id") }
                        )
                    }

                    composable(
                        route = "cadastro?id={id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                            defaultValue = -1
                        })
                    ) { entry ->
                        val id = entry.arguments?.getInt("id") ?: -1
                        TelaCadastro(
                            viewModel = viewModel,
                            tarefaId = if (id == -1) null else id,
                            onVoltar = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}