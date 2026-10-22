import java.util.Scanner;

public class DataTypes {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        boolean[] statusTasks = new boolean[100];
        int counterTasks = 0;
        String task = "";

        while (true) {
            showMenu();
            if (!scanner.hasNextInt()){
                System.out.println("Введите число:");
                scanner.nextLine();
                continue;
            }
            int choise = scanner.nextInt();
            scanner.nextLine(); // Enter убираем оставшийся

            switch (choise){
                case 1 -> counterTasks = addTask(counterTasks, task, tasks, statusTasks, scanner);
                case 2 -> showTasks(tasks, counterTasks, statusTasks);
                case 3 -> counterTasks = deleteTask(scanner, counterTasks, tasks, statusTasks);
                case 4 -> completeTask(scanner, counterTasks, statusTasks, tasks);
                case 0 -> {
                    System.out.println("Завершение работы.");
                    return;
                }
                default -> System.out.println("Неверный номер меню. Введите еще раз.");
            }

        }
    }

    public static void showMenu(){
        System.out.println(">>> Меню:\n" +
                "1. Добавить задачу\n" +
                "2. Показать все задачи\n" +
                "3. Удалить задачу (по номеру)\n" +
                "4. Отметить задачу как выполненную\n" +
                "0. Выход\n" +
                "\n" +
                "Выберите пункт меню:\n");
    }

    public static void showTasks(String[] tasks, int counterTasks, boolean[] statusTasks){
        {
            if (counterTasks == 0){
                System.out.println("Список задач пуст");
            } else {
                System.out.println("Список задач:");
                for (int i = 0; i < counterTasks; i++){
                    if (statusTasks[i] == true){
                        String statusTasksOne = "[X]";
                        System.out.println((i + 1) + ". " + statusTasksOne + " " + tasks[i]);
                    } else {
                        String statusTasksOne = "[ ]";
                        System.out.println((i + 1) + ". " + statusTasksOne + " " + tasks[i]);
                    }
                }
            }
        }
    }

    public static int addTask(
            int counterTasks,
            String task,
            String[] tasks,
            boolean[] statusTasks,
            Scanner scanner
    ){
        if (counterTasks >= 100){
            System.out.println("Уже хранится 100 задач.");
            return counterTasks;
        }
        System.out.println("Введите описание задачи:");
        task = scanner.nextLine();
        tasks[counterTasks] = task;
        statusTasks[counterTasks] = false;
        counterTasks++;
        System.out.println("Задача добавлена");
        return counterTasks;
    }

    public static void completeTask(
            Scanner scanner,
            int counterTasks,
            boolean[] statusTasks,
            String[] tasks
    ){
        System.out.println("Введите номер задачи для отметки:");
        if (!scanner.hasNextInt()){
            System.out.println("Введите номер задачи числом.");
            scanner.nextLine();
            return;
        }
        int taskNumber = scanner.nextInt();
        scanner.nextLine();
        if (taskNumber < 1 || taskNumber > counterTasks) {
            System.out.println("Некорректный номер задачи.");
            return;
        } else if (statusTasks[taskNumber - 1] == true){
            System.out.println("Задача " + tasks[taskNumber - 1] + " уже выполнена!");
        } else {
            statusTasks[taskNumber - 1] = true;
            System.out.println("Задача " + tasks[taskNumber - 1] + " отмечена как выполненная!");
        }
    }

    public static int deleteTask(
            Scanner scanner,
            int counterTasks,
            String[] tasks,
            boolean[] statusTasks
    ){
        System.out.println("Введите номер задачи для удаления:");
        if (!scanner.hasNextInt()){
            System.out.println("Введите номер задачи числом.");
            scanner.nextLine();
            return counterTasks;
        }
        int taskNumber = scanner.nextInt();
        scanner.nextLine();
        if (taskNumber < 1 || taskNumber > counterTasks){
            System.out.println("Некорректный номер задачи.");
            return counterTasks;
        } else {
            counterTasks--;
        }
        System.out.println("Задача " + tasks[taskNumber - 1] + " удалена!");
        tasks[taskNumber - 1] = null;
        for (int i = (taskNumber - 1); i < counterTasks; i++){
            if (tasks[i] == null && tasks[i + 1] != null){
                tasks[i] = tasks[i + 1];
                tasks[i + 1] = null;
                statusTasks[i] = statusTasks[i + 1];
                statusTasks[i + 1] = false;
            }
        }
        return counterTasks;
    }
}

