# Task Tracker CLI

Aplicativo de linha de comando feito em Java para controlar tarefas em uma lista de afazeres.

Este projeto foi baseado no desafio [Task Tracker](https://roadmap.sh/projects/task-tracker) do roadmap.sh.

## Objetivo

O objetivo do projeto e criar uma CLI simples para adicionar, atualizar, remover, listar e alterar o status de tarefas.

As tarefas sao armazenadas em um arquivo `tasks.json` criado no diretorio atual da aplicacao.

## Tecnologias

- Java
- Maven
- JSON em arquivo local
- File system nativo do Java

## Requisitos

- Java 17 ou superior
- Maven instalado

Para verificar as versoes:

```bash
java -version
mvn -version
```

## Como compilar

Na raiz do projeto, execute:

```bash
mvn clean package
```

O arquivo `.jar` sera gerado dentro da pasta `target`.

## Como executar

Na raiz do projeto, execute:

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar
```

No Windows PowerShell:

```powershell
java -jar target\task-tracker-cli-1.0-SNAPSHOT.jar
```

## Comandos

### Adicionar uma tarefa

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar add "Comprar mantimentos"
```

Saida esperada:

```text
Task added successfully (ID: 1)
```

### Atualizar uma tarefa

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar update 1 "Comprar mantimentos e preparar jantar"
```

### Remover uma tarefa

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar delete 1
```

### Marcar tarefa como em progresso

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-in-progress 1
```

### Marcar tarefa como concluida

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-done 1
```

### Listar todas as tarefas

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list
```

### Listar tarefas por status

```bash
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list done
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list todo
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list in-progress
```

## Status das tarefas

Uma tarefa pode ter um dos seguintes status:

| Status | Descricao |
| --- | --- |
| `todo` | Tarefa pendente |
| `in-progress` | Tarefa em andamento |
| `done` | Tarefa concluida |

## Estrutura da tarefa

Cada tarefa deve possuir os seguintes campos:

| Campo | Descricao |
| --- | --- |
| `id` | Identificador unico da tarefa |
| `description` | Descricao da tarefa |
| `status` | Status atual da tarefa |
| `createdAt` | Data e hora de criacao |
| `updatedAt` | Data e hora da ultima atualizacao |

Exemplo:

```json
{
  "id": 1,
  "description": "Comprar mantimentos",
  "status": "todo",
  "createdAt": "2026-09-13T12:00:00",
  "updatedAt": "2026-09-13T12:00:00"
}
```

## Arquivo de armazenamento

As tarefas sao salvas no arquivo:

```text
tasks.json
```

Se o arquivo nao existir, a aplicacao deve cria-lo automaticamente.

## Tratamento de erros

A aplicacao deve tratar casos como:

- Comando inexistente
- Argumentos obrigatorios ausentes
- ID invalido
- Tarefa nao encontrada
- Status invalido
- Arquivo `tasks.json` vazio ou inexistente

## Referencia

- [Task Tracker - roadmap.sh](https://roadmap.sh/projects/task-tracker)