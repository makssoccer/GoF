package org.example.behavioral;

import java.util.ArrayList;
import java.util.List;

/*    Паттерн Команда (Command) относится к категории поведенческих паттернов проектирования и используется для
инкапсуляции запроса как объекта, позволяя параметризовать клиентов с различными запросами, ставить запросы в очередь,
логировать запросы и поддерживать отмену операций.

    Структура паттерна:
1. Команда (Command):
-Определяет интерфейс для выполнения операции.
2. Конкретная команда (Concrete Command):
-Реализует интерфейс Command и определяет связь между объектом Receiver и действием.
-Реализует метод execute(), вызывая соответствующие операции объекта Receiver.
3. Получатель (Receiver):
-Знает, как выполнять операции, необходимые для выполнения запроса.
4. Инициатор (Invoker):
-Хранит команду и в определенный момент обращается к команде для выполнения запроса.
5. Клиент (Client):
-Создает объект ConcreteCommand и устанавливает его получателя.

    Пример использования паттерна Команда:
Рассмотрим пример с системой управления умным домом, где можно включать/выключать свет и управлять другими устройствами.*/

// Получатель - Свет
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is ON");
    }

    public void off() {
        System.out.println(location + " light is OFF");
    }
}

// Получатель - Телевизор
class Television {
    public void on() {
        System.out.println("Television is ON");
    }

    public void off() {
        System.out.println("Television is OFF");
    }

    public void setVolume(int level) {
        System.out.println("Television volume set to " + level);
    }
}

// Интерфейс команды
interface Command {
    void execute();
    void undo();
}

// Конкретная команда - Включить свет
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

// Конкретная команда - Выключить свет
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}

// Конкретная команда - Включить телевизор
class TelevisionOnCommand implements Command {
    private Television tv;

    public TelevisionOnCommand(Television tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.on();
        tv.setVolume(15);
    }

    public void undo() {
        tv.off();
    }
}

// Инициатор - Пульт управления
class RemoteController {
    private Command command;
    private List<Command> commandHistory = new ArrayList<>();

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
            commandHistory.add(command);
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
            System.out.println("Undo executed");
        } else {
            System.out.println("No commands to undo");
        }
    }
}

// Пример использования
public class CommandExample {
    public static void main(String[] args) {
        // Создаем получателей
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Television tv = new Television();

        // Создаем команды
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command tvOn = new TelevisionOnCommand(tv);

        // Создаем инициатор
        RemoteController remote = new RemoteController();

        // Выполняем команды
        System.out.println("--- Executing commands ---");
        remote.setCommand(livingRoomLightOn);
        remote.pressButton();

        remote.setCommand(kitchenLightOn);
        remote.pressButton();

        remote.setCommand(tvOn);
        remote.pressButton();

        // Отмена команд
        System.out.println("\n--- Undoing commands ---");
        remote.pressUndo();
        remote.pressUndo();
        remote.pressUndo();
        remote.pressUndo(); // Попытка отменить, когда история пуста
    }
}
/*
    Преимущества паттерна Команда:
1. Отделение объекта, инициирующего операцию, от объекта, который знает, как ее выполнить.
2. Легко добавлять новые команды без изменения существующих классов.
3. Возможность собирать команды в составные команды.
4. Поддержка отмены операций (undo/redo).
5. Возможность логирования изменений и восстановления после сбоя.

    Недостатки паттерна Команда:
1. Увеличение количества классов в системе из-за введения отдельного класса для каждой команды.
2. Усложнение кода программы.*/

