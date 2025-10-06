package org.example.structural;

import java.util.ArrayList;
import java.util.List;

/*    Паттерн Компоновщик (Composite) относится к категории структурных паттернов проектирования и используется для
компоновки объектов в древовидные структуры для представления иерархий часть-целое. Компоновщик позволяет клиентам
единообразно трактовать индивидуальные и составные объекты.

    Структура паттерна:
1. Компонент (Component):
-Определяет интерфейс для объектов композиции и для доступа к дочерним компонентам и управления ими.
-Может реализовывать стандартное поведение для всех классов.
2. Лист (Leaf):
-Представляет конечные объекты композиции. Лист не имеет дочерних компонентов.
-Определяет поведение примитивных объектов композиции.
3. Контейнер/Композит (Composite):
-Хранит дочерние компоненты и реализует операции для управления ими.
-Определяет поведение компонентов, имеющих потомков, и хранит дочерние компоненты.

    Пример использования паттерна Компоновщик:
Рассмотрим пример с файловой системой, где есть файлы (листья) и папки (композиты), которые могут содержать другие файлы и папки.*/

// Компонент
interface FileSystemComponent {
    void showDetails();
    int getSize();
}

// Лист - Файл
class File implements FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void showDetails() {
        System.out.println("File: " + name + " (Size: " + size + " KB)");
    }

    public int getSize() {
        return size;
    }
}

// Композит - Папка
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        components.add(component);
    }

    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    public void showDetails() {
        System.out.println("Folder: " + name + " (Total Size: " + getSize() + " KB)");
        for (FileSystemComponent component : components) {
            System.out.print("  ");
            component.showDetails();
        }
    }

    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

// Пример использования
public class CompositePattern {
    public static void main(String[] args) {
        // Создаем файлы
        File file1 = new File("Document.txt", 10);
        File file2 = new File("Image.jpg", 500);
        File file3 = new File("Video.mp4", 2000);

        // Создаем папки
        Folder folder1 = new Folder("My Documents");
        Folder folder2 = new Folder("Media");
        Folder rootFolder = new Folder("Root");

        // Добавляем файлы в папки
        folder1.add(file1);
        folder2.add(file2);
        folder2.add(file3);

        // Создаем иерархию
        rootFolder.add(folder1);
        rootFolder.add(folder2);

        // Добавляем еще один файл в корневую папку
        File file4 = new File("README.md", 5);
        rootFolder.add(file4);

        // Отображаем структуру
        System.out.println("File System Structure:");
        rootFolder.showDetails();

        System.out.println("\n--- Individual Folder ---");
        folder2.showDetails();
    }
}
/*
    Преимущества паттерна Компоновщик:
1. Упрощение работы с древовидными структурами: Клиентский код может единообразно работать как с простыми,
так и со сложными элементами дерева.
2. Упрощение добавления новых видов компонентов: Новые типы компонентов легко добавляются в систему.
3. Соответствие принципу открытости/закрытости: Можно добавлять новые типы компонентов без изменения существующего кода.

    Недостатки паттерна Компоновщик:
1. Сложность ограничения типов компонентов: Может быть сложно ограничить типы компонентов в композите.
2. Излишняя общность: Паттерн может сделать дизайн слишком общим, что затрудняет понимание того,
какие компоненты действительно могут быть добавлены в композит.*/

