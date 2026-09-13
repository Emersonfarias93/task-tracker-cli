package br.com.esdevcode;

import java.util.Arrays;

public class TaskCliApplication
{
    public static void main( String[] args ) {
            if (args.length == 0) {
                printHelp();
                return;
            }

            String command = args[0];

            switch (command) {
                case "add":
                    handleAdd(args);
                    break;

                case "update":
                case "delete":
                case "mark-in-progress":
                case "mark-done":
                case "list":
                    System.out.println(
                            "O comando '" + command + "' será implementado em seguida."
                    );
                    break;

                default:
                    System.err.println("Comando desconhecido: " + command);
                    printHelp();
            }
        }

        private static void handleAdd (String[]args){
            if (args.length < 2) {
                System.err.println("Informe a descrição da tarefa.");
                System.err.println("Exemplo: add \"Comprar mantimentos\"");
                return;
            }

            String description = String.join(
                    " ",
                    Arrays.copyOfRange(args, 1, args.length)
            );

            if (description.trim().isEmpty()) {
                System.err.println("A descrição não pode estar vazia.");
                return;
            }

            System.out.println("Descrição recebida: " + description);
        }

        private static void printHelp() {
            System.out.println("Task Tracker CLI");
            System.out.println();
            System.out.println("Comandos disponíveis:");
            System.out.println("  add \"descrição\"");
            System.out.println("  update <id> \"descrição\"");
            System.out.println("  delete <id>");
            System.out.println("  mark-in-progress <id>");
            System.out.println("  mark-done <id>");
            System.out.println("  list");
            System.out.println("  list todo");
            System.out.println("  list in-progress");
            System.out.println("  list done");
        }
}
