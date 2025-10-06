package org.example.behavioral;
import java.util.NoSuchElementException;

/*    Паттерн Iterator относится к категории поведенческих паттернов проектирования.Он используется для обхода элементов коллекции
без раскрытия её внутреннего представления.

    Структура паттерна Iterator:
1)Интерфейс Итератора(Iterator):
-Определяет методы для перебора элементов коллекции.
2)Интерфейс Коллекции(Collection):
-Определяет методы для создания итератора.
3)Конкретный Итератор(Concrete Iterator):
-Реализует интерфейс итератора и обеспечивает перебор элементов коллекции.
4)Конкретная Коллекция(Concrete Collection):
-Реализует интерфейс коллекции и предоставляет конкретную реализацию методов для создания итератора.

    Пример использования паттерна Iterator:
Рассмотрим пример с созданием итератора для перебора элементов массива.*/

// Интерфейс итератора
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Интерфейс коллекции
interface Collection<T> {
    Iterator<T> createIterator();
}

// Конкретный итератор для массива
class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int position = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    public boolean hasNext() {
        return position < array.length;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[position++];
    }
}

// Конкретная коллекция для массива
class ArrayCollection<T> implements Collection<T> {
    private T[] array;

    public ArrayCollection(T[] array) {
        this.array = array;
    }

    public Iterator<T> createIterator() {
        return new ArrayIterator<>(array);
    }
}

// Пример использования
public class IteratorPattern {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};
        Collection<Integer> collection = new ArrayCollection<>(array);
        Iterator<Integer> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
/*В этом примере ArrayCollection представляет конкретную коллекцию для массива, а ArrayIterator представляет конкретный итератор для этой коллекции.
Клиентский код создает коллекцию, а затем использует итератор для перебора элементов этой коллекции.
Таким образом, паттерн Iterator позволяет отделить алгоритм перебора от структуры коллекции,
что делает код более гибким и позволяет использовать разные алгоритмы перебора для одной и той же коллекции.*/

