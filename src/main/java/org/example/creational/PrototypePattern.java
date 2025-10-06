package org.example.creational;

/*    Паттерн Прототип (Prototype) относится к категории порождающих паттернов проектирования и используется для создания
новых объектов путем копирования существующих объектов, называемых прототипами. Этот паттерн обеспечивает способ создания объектов,
избегая дорогостоящих операций инициализации.

    Структура паттерна Прототип:
1. Прототип (Prototype):
- Определяет интерфейс для клонирования самого себя.
2. Конкретный Прототип (Concrete Prototype):
- Реализует метод клонирования себя.
3. Клиент (Client):
-Создает новые объекты, вызывая метод клонирования прототипа.

     Пример реализации паттерна Прототип в Java:*/

// Прототип
interface PrototypeInterface {
    PrototypeInterface clone();
}

// Конкретный прототип
class ConcretePrototype implements PrototypeInterface {
    private int field;

    public ConcretePrototype(int field) {
        this.field = field;
    }

    @Override
    public PrototypeInterface clone() {
        // Создание и возвращение копии текущего объекта
        return new ConcretePrototype(this.field);
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getField() {
        return this.field;
    }
}

// Пример использования
public class PrototypePattern {
    public static void main(String[] args) {
        // Создание прототипа
        ConcretePrototype prototype = new ConcretePrototype(10);

        // Клонирование прототипа
        ConcretePrototype clone = (ConcretePrototype) prototype.clone();

        // Вывод значений полей оригинала и клонированного объекта
        System.out.println("Original field value: " + prototype.getField());
        System.out.println("Cloned field value: " + clone.getField());

        // Изменение значения поля у клонированного объекта
        clone.setField(20);

        // Вывод значений полей оригинала и клонированного объекта после изменения
        System.out.println("Original field value after cloning: " + prototype.getField());
        System.out.println("Cloned field value after cloning: " + clone.getField());
    }
}
/*
    Преимущества паттерна Прототип:
1. Уменьшение затрат на создание объектов: Паттерн Прототип позволяет создавать новые объекты, копируя существующие, что может существенно сократить затраты на создание объектов.
2. Избежание сложностей при наследовании: Классы, использующие прототипы, могут создавать копии себя без необходимости знания о конкретных классах объектов.
3. Увеличение гибкости приложения: Паттерн Прототип позволяет создавать объекты динамически во время выполнения программы, что увеличивает гибкость приложения.

    Недостатки паттерна Прототип:
1. Сложность копирования сложных объектов: Если объект содержит сложные вложенные структуры данных или ссылки на другие объекты, копирование такого объекта может быть нетривиальной задачей.*/

