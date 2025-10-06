package org.example.creational;

/*    Паттерн "Абстрактная фабрика" (Abstract Factory) относится к категории порождающих паттернов проектирования и предоставляет интерфейс
для создания семейств взаимосвязанных или взаимозависимых объектов без указания их конкретных классов. Этот паттерн предоставляет абстрактную фабрику,
которая создает семейства связанных объектов, а конкретные фабрики реализуют этот интерфейс, создавая объекты определенных классов.

    Структура паттерна:
1. Абстрактная Фабрика (Abstract Factory):
-Определяет интерфейс для создания семейства взаимосвязанных или взаимозависимых объектов без указания их конкретных классов.
2. Конкретная Фабрика (Concrete Factory):
-Реализует методы создания объектов для конкретного семейства продуктов. Каждая конкретная фабрика соответствует одному семейству продуктов.
3. Продукт (Product):
-Определяет интерфейс объектов, создаваемых абстрактной фабрикой.
4. Конкретный Продукт (Concrete Product):
-Реализует интерфейс продукта и представляет собой конкретную реализацию объекта, создаваемого конкретной фабрикой.

        Пример:
Рассмотрим пример создания мебельного магазина с использованием абстрактной фабрики:*/

// Абстрактная фабрика для создания мебельных объектов
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}

// Конкретная фабрика для создания мебельных объектов в стиле модерн
class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new ModernChair();
    }
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

// Конкретная фабрика для создания мебельных объектов в стиле викторианском
class VictorianFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new VictorianChair();
    }
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}

// Абстрактные продукты
interface Chair {
    void sitOn();
}

interface Sofa {
    void lieOn();
}

// Конкретные продукты
class ModernChair implements Chair {
    public void sitOn() {
        System.out.println("Sitting on a modern chair");
    }
}

class ModernSofa implements Sofa {
    public void lieOn() {
        System.out.println("Lying on a modern sofa");
    }
}

class VictorianChair implements Chair {
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair");
    }
}

class VictorianSofa implements Sofa {
    public void lieOn() {
        System.out.println("Lying on a Victorian sofa");
    }
}

// Пример использования абстрактной фабрики
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        // Создаем фабрику мебели в стиле модерн
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Sofa modernSofa = modernFactory.createSofa();
        
        modernChair.sitOn();
        modernSofa.lieOn();
        
        System.out.println();
        
        // Создаем фабрику мебели в викторианском стиле
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Chair victorianChair = victorianFactory.createChair();
        Sofa victorianSofa = victorianFactory.createSofa();
        
        victorianChair.sitOn();
        victorianSofa.lieOn();
    }
}
/*
    В этом примере FurnitureFactory - это абстрактная фабрика, которая определяет методы создания мебельных объектов.
ModernFurnitureFactory и VictorianFurnitureFactory - это конкретные фабрики, которые реализуют этот интерфейс и создают
объекты определенного стиля мебели. Клиентский код использует абстрактную фабрику
для создания мебельных объектов без указания их конкретных классов.*/

