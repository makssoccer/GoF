package org.example.structural;

/*  Паттерн Фасад (Facade) относится к категории структурных паттернов проектирования и предоставляет унифицированный интерфейс
для доступа к группе интерфейсов подсистемы. Он упрощает использование сложной подсистемы, предоставляя более высокоуровневый интерфейс.

    Структура паттерна:
1. Фасад (Facade):
-Предоставляет унифицированный интерфейс для доступа к функциональности подсистемы.
2. Подсистема (Subsystem):
-Состоит из различных компонентов, которые могут быть сложными и непосредственно не доступны клиентам. Фасад скрывает детали реализации подсистемы от клиентов.
    Пример использования паттерна Фасад:
Рассмотрим пример с использованием фасада для управления компьютером.*/

// Подсистема
class CPU {
    public void processData() {
        System.out.println("CPU is processing data");
    }
}

// Подсистема
class Memory {
    public void load() {
        System.out.println("Memory is loading data");
    }
}

// Подсистема
class HardDrive {
    public void readData() {
        System.out.println("HardDrive is reading data");
    }
}

// Фасад
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.processData();
        memory.load();
        hardDrive.readData();
        System.out.println("Computer is starting...");
    }
}

// Пример использования
public class Facade {
    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
    }
}
/*  В этом примере фасад ComputerFacade предоставляет унифицированный интерфейс для запуска компьютера.
Он скрывает сложность подсистемы (CPU, Memory, HardDrive) от клиента, который просто вызывает метод start() фасада для запуска компьютера.
Это позволяет упростить использование подсистемы и сделать код клиента более понятным.*/

