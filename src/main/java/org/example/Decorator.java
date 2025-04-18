package org.example;

/*  Паттерн Декоратор (Decorator) относится к категории структурных паттернов проектирования и используется для динамического
добавления нового функционала объекту без изменения его основной структуры. Он предоставляет гибкую альтернативу наследованию для расширения функциональности класса.

    Структура паттерна:
1. Компонент (Component):
-Определяет интерфейс для объектов, которые могут иметь новую функциональность добавленную с помощью Декоратора.
2. Конкретный компонент (Concrete Component):
-Представляет объект, к которому добавляется новая функциональность.
3. Декоратор (Decorator):
-Содержит ссылку на объект типа Component и имеет такой же интерфейс, что и компонент, который он декорирует.
4. Конкретный декоратор (Concrete Decorator):
-Добавляет новую функциональность к объекту Component.

    Пример использования паттерна Декоратор:
Рассмотрим пример с созданием кофейного магазина, где мы можем заказать разные виды кофе с различными добавками (декораторами).*/

// Интерфейс для кофе
interface Coffee {
    double cost();
    String getDescription();
}

// Конкретная реализация кофе
class SimpleCoffee implements Coffee {
    public double cost() {
        return 1.0;
    }

    public String getDescription() {
        return "Simple coffee";
    }
}

// Декоратор для добавки молока
class MilkDecorator implements Coffee {
    private Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost() + 0.5;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
}

// Декоратор для добавки шоколада
class ChocolateDecorator implements Coffee {
    private Coffee coffee;

    public ChocolateDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost() + 0.7;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Chocolate";
    }
}

// Пример использования
public class Decorator {
    public static void main(String[] args) {
        // Простой кофе
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: " + coffee.cost() + ", Description: " + coffee.getDescription());

        // Кофе с молоком
        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("Cost: " + milkCoffee.cost() + ", Description: " + milkCoffee.getDescription());

        // Кофе с молоком и шоколадом
        Coffee chocolateMilkCoffee = new ChocolateDecorator(milkCoffee);
        System.out.println("Cost: " + chocolateMilkCoffee.cost() + ", Description: " + chocolateMilkCoffee.getDescription());
    }
}
/*  В этом примере SimpleCoffee представляет базовый компонент, а MilkDecorator и ChocolateDecorator представляют конкретные декораторы,
которые добавляют молоко и шоколад к базовому кофе. Каждый новый декоратор добавляет новую функциональность к объекту без изменения его базовой структуры.*/