package org.example.structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*    Паттерн Легковес (Flyweight) относится к категории структурных паттернов проектирования и используется для эффективной
поддержки большого числа мелких объектов за счет разделения общего состояния объектов. Flyweight позволяет экономить память,
разделяя общее состояние объектов между собой, вместо хранения одинаковых данных в каждом объекте.

    Структура паттерна:
1. Легковес (Flyweight):
-Определяет интерфейс, через который легковесы могут получать внешнее состояние и работать с ним.
2. Конкретный легковес (Concrete Flyweight):
-Реализует интерфейс Flyweight и хранит внутреннее состояние. Объект должен быть разделяемым.
-Внутреннее состояние не зависит от контекста и может быть разделено между объектами.
3. Неразделяемый легковес (Unshared Concrete Flyweight):
-Не все объекты Flyweight должны быть разделяемыми. Некоторые объекты могут не разделяться.
4. Фабрика легковесов (Flyweight Factory):
-Создает объекты Flyweight и управляет ими. Обеспечивает правильное разделение легковесов.
-Когда клиент запрашивает легковес, фабрика возвращает существующий экземпляр или создает новый, если он еще не существует.
5. Клиент (Client):
-Хранит ссылки на легковесы и вычисляет или хранит их внешнее состояние.

    Пример использования паттерна Легковес:
Рассмотрим пример с рисованием деревьев в игре. У деревьев есть общие характеристики (тип, цвет, текстура),
которые можно разделить, и уникальные характеристики (координаты), которые нужно хранить отдельно.*/

// Легковес - Тип дерева
class TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing tree '" + name + "' of color '" + color + 
                          "' with texture '" + texture + "' at position (" + x + ", " + y + ")");
    }
}

// Фабрика легковесов
class TreeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "_" + color + "_" + texture;
        TreeType type = treeTypes.get(key);

        if (type == null) {
            type = new TreeType(name, color, texture);
            treeTypes.put(key, type);
            System.out.println("Creating new TreeType: " + key);
        } else {
            System.out.println("Reusing existing TreeType: " + key);
        }

        return type;
    }

    public static int getTreeTypeCount() {
        return treeTypes.size();
    }
}

// Контекстный объект - Дерево
class Tree {
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}

// Лес - Коллекция деревьев
class Forest {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void draw() {
        System.out.println("\nDrawing forest with " + trees.size() + " trees:");
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

// Пример использования
public class Flyweight {
    public static void main(String[] args) {
        Forest forest = new Forest();

        // Сажаем деревья одного типа в разных местах
        forest.plantTree(10, 20, "Oak", "Green", "Rough");
        forest.plantTree(50, 60, "Oak", "Green", "Rough");
        forest.plantTree(100, 120, "Oak", "Green", "Rough");

        // Сажаем деревья другого типа
        forest.plantTree(30, 40, "Pine", "Dark Green", "Smooth");
        forest.plantTree(70, 80, "Pine", "Dark Green", "Smooth");

        // Сажаем еще одно дерево первого типа
        forest.plantTree(150, 160, "Oak", "Green", "Rough");

        // Рисуем лес
        forest.draw();

        // Выводим количество созданных типов деревьев
        System.out.println("\nTotal tree types created: " + TreeFactory.getTreeTypeCount());
        System.out.println("Memory saved by using Flyweight pattern!");
    }
}
/*
    Преимущества паттерна Легковес:
1. Экономия памяти: Основное преимущество - значительная экономия оперативной памяти за счет разделения общего состояния.
2. Улучшение производительности: Меньшее потребление памяти может привести к улучшению производительности,
особенно при работе с большим количеством объектов.

    Недостатки паттерна Легковес:
1. Усложнение кода: Паттерн усложняет код программы, так как требует разделения состояния на внутреннее и внешнее.
2. Дополнительные вычисления: Может потребоваться больше времени на вычисление внешнего состояния при каждом обращении к объекту.
3. Требует тщательного проектирования: Необходимо тщательно продумать, какое состояние будет внутренним, а какое - внешним.

    Когда использовать:
- Когда приложение использует большое количество объектов.
- Когда расходы на хранение объектов высоки из-за большого количества объектов.
- Когда большую часть состояния объектов можно сделать внешним.
- Когда приложение не зависит от идентичности объектов (объекты могут быть заменяемыми).*/

