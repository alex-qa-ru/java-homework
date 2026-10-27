import java.util.Scanner;

public class DataTypes {

    private static int counterTasks = 0;
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static boolean[] statusTasks = new boolean[MAX_TASKS];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            showMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Введите число:");
                scanner.nextLine();
                continue;
            }
            int choise = scanner.nextInt();
            scanner.nextLine(); // Enter убираем оставшийся

            switch (choise) {
                case 1 -> addTask();
                case 2 -> showTasks();
                case 3 -> deleteTask();
                case 4 -> completeTask();
                case 0 -> {
                    System.out.println("Завершение работы.");
                    return;
                }
                default -> System.out.println("Неверный номер меню. Введите еще раз.");
            }

        }
    }

    private static void showMenu() {
        System.out.println(">>> Меню:\n" +
                "1. Добавить задачу\n" +
                "2. Показать все задачи\n" +
                "3. Удалить задачу (по номеру)\n" +
                "4. Отметить задачу как выполненную\n" +
                "0. Выход\n" +
                "\n" +
                "Выберите пункт меню:\n");
    }

    private static void showTasks() {
        if (counterTasks == 0) {
            System.out.println("Список задач пуст");
            return;
        }
        System.out.println("Список задач:");
        for (int i = 0; i < counterTasks; i++) {
            if (statusTasks[i]) {
                String statusTasksOne = "[X]";
                System.out.println((i + 1) + ". " + statusTasksOne + " " + tasks[i]);
            } else {
                String statusTasksOne = "[ ]";
                System.out.println((i + 1) + ". " + statusTasksOne + " " + tasks[i]);
            }
        }
    }

    private static void addTask() {
        String task = "";

        if (counterTasks >= MAX_TASKS) {
            System.out.println("Уже хранится " + MAX_TASKS + " задач.");
            return;
        }
        System.out.println("Введите описание задачи:");
        task = scanner.nextLine();
        tasks[counterTasks] = task;
        statusTasks[counterTasks] = false;
        counterTasks++;
        System.out.println("Задача добавлена");
    }

    private static void completeTask() {
        System.out.println("Введите номер задачи для отметки:");
        if (!scanner.hasNextInt()) {
            System.out.println("Введите номер задачи числом.");
            scanner.nextLine();
            return;
        }
        int taskNumber = scanner.nextInt();
        scanner.nextLine();
        if (taskNumber < 1 || taskNumber > counterTasks) {
            System.out.println("Некорректный номер задачи.");
            return;
        }
        if (statusTasks[taskNumber - 1]) {
            System.out.println("Задача " + tasks[taskNumber - 1] + " уже выполнена!");
        } else {
            statusTasks[taskNumber - 1] = true;
            System.out.println("Задача " + tasks[taskNumber - 1] + " отмечена как выполненная!");
        }
    }

    private static void deleteTask() {
        System.out.println("Введите номер задачи для удаления:");
        if (!scanner.hasNextInt()) {
            System.out.println("Введите номер задачи числом.");
            scanner.nextLine();
            return;
        }
        int taskNumber = scanner.nextInt();
        scanner.nextLine();
        if (taskNumber < 1 || taskNumber > counterTasks) {
            System.out.println("Некорректный номер задачи.");
            return;
        }
        counterTasks--;
        System.out.println("Задача " + tasks[taskNumber - 1] + " удалена!");
        for (int i = (taskNumber - 1); i < counterTasks; i++) {
            tasks[i] = tasks[i + 1];
            tasks[i + 1] = null;
            statusTasks[i] = statusTasks[i + 1];
            statusTasks[i + 1] = false;
        }
        tasks[counterTasks] = null;
        statusTasks[counterTasks] = false;
    }
}

