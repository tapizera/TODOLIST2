# TodoList - Projeto Final do Mód. II

**Aluno:** Davi Falcão
**Matrícula:** (vish, não sei)
**Data de entrega:** 08/10/2026

## Justificativa do tema
Porque é um tipo de app que eu mesmo estou precisando, acho que com minhas próprias ideias talvez eu consiga criar o app ideal
que estou buscando, não só como uma Lista de Tarefas simples mas algo mais completo que possa realmente substituir meus vários apps de gerenciamento de vida

## Como funciona
- **Tela de lista:** mostra as tarefas salvas, com checkbox para concluir, botão de excluir e botão + para adicionar
- **Tela de cadastro/edição:** campos de título e descrição, com botão salvar
- Os dados ficam salvos no celular com Room, então continuam lá depois de fechar o app

## Tecnologias
Kotlin, Jetpack Compose, Navigation Compose, Room, MVVM

## Estrutura
- `data/`: Tarefa (entidade), TarefaDao, AppDatabase, TarefaRepository
- `viewmodel/`: TarefaViewModel
- `ui/screens/`: TelaLista, TelaCadastro
