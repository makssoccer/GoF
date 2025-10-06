package org.example.creational;

/*    Шаблон Фабрика (Factory) - это порождающий паттерн проектирования, который предоставляет интерфейс для создания объектов
некоторого семейства классов, при этом позволяя подклассам выбирать конкретный класс для создания. Он предназначен для
решения задачи создания объектов без явного указания их класса в коде, делая код более гибким и менее зависимым от конкретных реализаций.

    Структура шаблона Фабрика:
1)Фабрика (Factory): определяет методы для создания объектов. Может быть интерфейсом или абстрактным классом.
2)Конкретная фабрика (Concrete Factory): реализует методы создания объектов для конкретных классов.
3)Продукт (Product): абстрактный класс или интерфейс, представляющий создаваемый объект.
4)Конкретный продукт (Concrete Product): конкретная реализация продукта.

        Пример использования шаблона Фабрика на Java:*/

interface Shape {
    void draw();
}

// Конкретные продукты
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

// Фабрика
class ShapeFactory {
    // Метод для создания объектов
    public Shape createShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("Circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("Square")) {
            return new Square();
        }
        return null;
    }
}

// Пример использования
public class FactoryPattern {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape circle = factory.createShape("Circle");
        circle.draw(); // Output: Drawing a Circle

        Shape square = factory.createShape("Square");
        square.draw(); // Output: Drawing a Square
    }
}
/*
    Преимущества шаблона Фабрика:
1. Изоляция создания объектов: Клиентский код работает с интерфейсом фабрики, что позволяет изолировать его от конкретных классов продуктов.
2. Гибкость кода: Шаблон Фабрика делает код более гибким, поскольку позволяет легко добавлять новые классы продуктов или изменять существующие, не изменяя клиентский код.
3. Повышение уровня абстракции: Фабрика скрывает детали создания объектов, предоставляя только интерфейс для их создания.
4. Шаблон Фабрика широко используется в Java и других языках программирования для создания объектов с использованием абстракции и расширяемости.*/

