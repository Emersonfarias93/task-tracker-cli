# Task CLI Application

## English

This is a simple command-line interface (CLI) application for managing tasks.
You can add, update, delete, mark, and list tasks directly from the terminal.

This project was built based on the [Task Tracker](https://roadmap.sh/projects/task-tracker) challenge from roadmap.sh.

### Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as `in-progress` or `done`.
- **List Tasks:** List all tasks or filter them by status: `todo`, `in-progress`, or `done`.
- **Persist Tasks:** Store tasks in a local `tasks.json` file.

### Installation

1. **Clone the repository:**

   ```bash
   git clone <your-repository-url>
   cd task-tracker-cli
   ```

2. **Compile the source code:**

   ```bash
   mvn clean package
   ```

3. **Run the application:**

   ```bash
   java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar <command> [arguments]
   ```

   On Windows PowerShell:

   ```powershell
   java -jar target\task-tracker-cli-1.0-SNAPSHOT.jar <command> [arguments]
   ```

### Usage

```bash
# Adding a new task
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating a task
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar update 1 "Buy groceries and cook dinner"
# Output: Task updated successfully (ID: 1)

# Deleting a task
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar delete 1
# Output: Task deleted successfully (ID: 1)

# Marking a task as in progress
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-in-progress 1
# Output: Task marked as in progress (ID: 1)

# Marking a task as done
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-done 1
# Output: Task marked as done (ID: 1)

# Listing all tasks
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list
# Output: List of all tasks

# Listing tasks by status
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list todo
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list in-progress
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list done
```

### Task Properties

Each task has the following properties:

| Property | Description |
| --- | --- |
| `id` | Unique task identifier |
| `description` | Task description |
| `status` | Current task status |
| `createdAt` | Date and time when the task was created |
| `updatedAt` | Date and time when the task was last updated |

### Task Status

The available task statuses are:

- `todo`
- `in-progress`
- `done`

---

## Português

Esta e uma aplicacao simples de linha de comando (CLI) para gerenciamento de tarefas.
Voce pode adicionar, atualizar, remover, marcar e listar tarefas diretamente pelo terminal.

Este projeto foi desenvolvido com base no desafio [Task Tracker](https://roadmap.sh/projects/task-tracker) do roadmap.sh.

### Funcionalidades

- **Adicionar uma tarefa:** Adiciona uma nova tarefa com uma descricao.
- **Atualizar uma tarefa:** Atualiza a descricao de uma tarefa existente.
- **Remover uma tarefa:** Remove uma tarefa pelo ID.
- **Marcar uma tarefa:** Marca uma tarefa como `in-progress` ou `done`.
- **Listar tarefas:** Lista todas as tarefas ou filtra por status: `todo`, `in-progress` ou `done`.
- **Persistir tarefas:** Armazena as tarefas em um arquivo local `tasks.json`.

### Instalacao

1. **Clone o repositorio:**

   ```bash
   git clone <url-do-seu-repositorio>
   cd task-tracker-cli
   ```

2. **Compile o codigo-fonte:**

   ```bash
   mvn clean package
   ```

3. **Execute a aplicacao:**

   ```bash
   java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar <comando> [argumentos]
   ```

   No Windows PowerShell:

   ```powershell
   java -jar target\task-tracker-cli-1.0-SNAPSHOT.jar <comando> [argumentos]
   ```

### Como usar

```bash
# Adicionar uma nova tarefa
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar add "Comprar mantimentos"
# Saida: Task added successfully (ID: 1)

# Atualizar uma tarefa
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar update 1 "Comprar mantimentos e preparar jantar"
# Saida: Task updated successfully (ID: 1)

# Remover uma tarefa
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar delete 1
# Saida: Task deleted successfully (ID: 1)

# Marcar uma tarefa como em andamento
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-in-progress 1
# Saida: Task marked as in progress (ID: 1)

# Marcar uma tarefa como concluida
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-done 1
# Saida: Task marked as done (ID: 1)

# Listar todas as tarefas
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list
# Saida: Lista todas as tarefas

# Listar tarefas por status
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list todo
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list in-progress
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list done
```

### Propriedades da tarefa

Cada tarefa possui as seguintes propriedades:

| Propriedade | Descricao |
| --- | --- |
| `id` | Identificador unico da tarefa |
| `description` | Descricao da tarefa |
| `status` | Status atual da tarefa |
| `createdAt` | Data e hora em que a tarefa foi criada |
| `updatedAt` | Data e hora da ultima atualizacao da tarefa |

### Status da tarefa

Os status disponiveis sao:

- `todo`
- `in-progress`
- `done`

---

## Project Structure / Estrutura do Projeto

```text
task-tracker-cli
├── .github
│   └── workflows
│       └── release.yml
├── .mvn
├── src
│   ├── main
│   │   └── java
│   │       └── br
│   │           └── com
│   │               └── esdevcode
│   │                   ├── model
│   │                   │   ├── Task.java
│   │                   │   └── TaskStatus.java
│   │                   ├── repository
│   │                   │   └── TaskRepository.java
│   │                   ├── service
│   │                   │   └── TaskService.java
│   │                   └── TaskCliApplication.java
│   └── test
├── .gitignore
├── pom.xml
└── README.md
```

## Reference / Referencia

- [Task Tracker - roadmap.sh](https://roadmap.sh/projects/task-tracker)
