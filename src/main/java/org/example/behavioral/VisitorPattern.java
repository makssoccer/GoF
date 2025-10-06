package org.example.behavioral; 

import java.util.ArrayList;
import java.util.List;

/*    Паттерн Посетитель (Visitor) относится к категории поведенческих паттернов проектирования и используется для
описания операций, выполняемых над элементами некоторой структуры объектов. Посетитель позволяет определить новую
операцию без изменения классов этих объектов.

    Структура паттерна:
1. Посетитель (Visitor):
-Определяет интерфейс для операций, которые должны быть выполнены над элементами структуры объектов.
2. Конкретный посетитель (Concrete Visitor):
-Реализует операции, определенные в интерфейсе Visitor.
-Каждая операция реализует часть алгоритма, определенного для соответствующего класса объекта в структуре.
3. Элемент (Element):
-Определяет метод accept(), который принимает посетителя в качестве аргумента.
4. Конкретный элемент (Concrete Element):
-Реализует метод accept().
5. Объектная структура (Object Structure):
-Может перечислить свои элементы и предоставить высокоуровневый интерфейс, позволяющий посетителю посетить его элементы.

    Пример использования паттерна Посетитель:
Рассмотрим пример с различными типами компонентов компьютера и операциями над ними (расчет цены, экспорт в XML).*/

// Интерфейс посетителя
interface ComputerPartVisitor {
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
    void visit(Mouse mouse);
    void visit(Computer computer);
}

// Интерфейс элемента
interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}

// Конкретный элемент - Клавиатура
class Keyboard implements ComputerPart {
    private double price;

    public Keyboard(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

// Конкретный элемент - Монитор
class Monitor implements ComputerPart {
    private double price;
    private int screenSize;

    public Monitor(double price, int screenSize) {
        this.price = price;
        this.screenSize = screenSize;
    }

    public double getPrice() {
        return price;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

// Конкретный элемент - Мышь
class Mouse implements ComputerPart {
    private double price;

    public Mouse(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

// Конкретный элемент - Компьютер (композитный элемент)
class Computer implements ComputerPart {
    private List<ComputerPart> parts = new ArrayList<>();

    public Computer() {
        parts.add(new Keyboard(50.0));
        parts.add(new Monitor(300.0, 24));
        parts.add(new Mouse(25.0));
    }

    public void accept(ComputerPartVisitor visitor) {
        for (ComputerPart part : parts) {
            part.accept(visitor);
        }
        visitor.visit(this);
    }
}

// Конкретный посетитель - Расчет стоимости
class PricingVisitor implements ComputerPartVisitor {
    private double totalPrice = 0;

    public void visit(Keyboard keyboard) {
        totalPrice += keyboard.getPrice();
        System.out.println("Keyboard price: $" + keyboard.getPrice());
    }

    public void visit(Monitor monitor) {
        totalPrice += monitor.getPrice();
        System.out.println("Monitor price: $" + monitor.getPrice() + " (Screen size: " + monitor.getScreenSize() + "\")");
    }

    public void visit(Mouse mouse) {
        totalPrice += mouse.getPrice();
        System.out.println("Mouse price: $" + mouse.getPrice());
    }

    public void visit(Computer computer) {
        System.out.println("Total computer price: $" + totalPrice);
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

// Конкретный посетитель - Экспорт в XML
class XmlExportVisitor implements ComputerPartVisitor {
    private StringBuilder xml = new StringBuilder();

    public void visit(Keyboard keyboard) {
        xml.append("<Keyboard price=\"").append(keyboard.getPrice()).append("\"/>\n");
    }

    public void visit(Monitor monitor) {
        xml.append("<Monitor price=\"").append(monitor.getPrice())
           .append("\" screenSize=\"").append(monitor.getScreenSize()).append("\"/>\n");
    }

    public void visit(Mouse mouse) {
        xml.append("<Mouse price=\"").append(mouse.getPrice()).append("\"/>\n");
    }

    public void visit(Computer computer) {
        // Оборачиваем все части в тег Computer
    }

    public String getXml() {
        return "<Computer>\n" + xml.toString() + "</Computer>";
    }
}

// Конкретный посетитель - Операция отображения
class DisplayVisitor implements ComputerPartVisitor {
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard");
    }

    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor");
    }

    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse");
    }

    public void visit(Computer computer) {
        System.out.println("Displaying Computer");
    }
}

// Пример использования
public class VisitorPattern {
    public static void main(String[] args) {
        Computer computer = new Computer();

        // Применяем посетителя для расчета цены
        System.out.println("=== Price Calculation ===");
        PricingVisitor pricingVisitor = new PricingVisitor();
        computer.accept(pricingVisitor);

        // Применяем посетителя для отображения
        System.out.println("\n=== Display Operation ===");
        DisplayVisitor displayVisitor = new DisplayVisitor();
        computer.accept(displayVisitor);

        // Применяем посетителя для экспорта в XML
        System.out.println("\n=== XML Export ===");
        XmlExportVisitor xmlVisitor = new XmlExportVisitor();
        computer.accept(xmlVisitor);
        System.out.println(xmlVisitor.getXml());
    }
}
/*
    Преимущества паттерна Посетитель:
1. Упрощение добавления новых операций: Можно легко добавлять новые операции, не изменяя классы элементов.
2. Группировка связанных операций: Родственные операции собраны в одном классе посетителя.
3. Накопление состояния: Посетитель может накапливать состояние при обходе структуры объектов.
4. Разделение ответственности: Операции отделены от структуры объектов.

    Недостатки паттерна Посетитель:
1. Сложность добавления новых элементов: Добавление нового класса элементов требует изменения интерфейса посетителя
и всех его конкретных классов.
2. Нарушение инкапсуляции: Посетителю может потребоваться доступ к внутреннему состоянию элементов.
3. Усложнение кода: Может привести к большому количеству классов посетителей.

    Когда использовать:
- Когда нужно выполнить много различных и не связанных операций над объектами сложной структуры.
- Когда структура объектов редко изменяется, но часто добавляются новые операции.
- Когда классы, определяющие структуру объектов, редко изменяются.*/

