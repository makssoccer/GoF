package org.example.structural;

/*
    Паттерн Proxy (Заместитель) относится к категории структурных паттернов проектирования и используется для предоставления заместителя или местахранителя для другого объекта,
чтобы контролировать доступ к нему.

    Структура паттерна:
1. Субъект (Subject):
-Определяет общий интерфейс для реального объекта и его заместителя, чтобы заместитель мог быть использован везде, где ожидается реальный объект.
2. Реальный объект (Real Subject):
-Представляет реальный объект, к которому осуществляется доступ через заместителя.
3. Заместитель (Proxy):
-Предоставляет заместителя для реального объекта и контролирует доступ к нему. Заместитель может выполнить какую-то дополнительную логику до или после вызова методов реального объекта.

    Пример использования паттерна Proxy:
Предположим, у нас есть интерфейс Image и его реализация RealImage, которая загружает изображение с диска.
Для оптимизации производительности мы можем создать прокси-объект ProxyImage, который загружает изображение только при первом доступе.*/

// Интерфейс для изображения
interface Image {
    void display();
}

// Реальная реализация изображения
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
    }

    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}

// Прокси для изображения
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// Пример использования
public class ProxyPattern {
    public static void main(String[] args) {
        // Создание прокси-объекта
        Image image = new ProxyImage("test.jpg");

        // Изображение будет загружено только при первом вызове display()
        System.out.println("First call to display():");
        image.display();

        System.out.println("\nSecond call to display():");
        // При втором вызове изображение уже загружено
        image.display();
    }
}
/*В этом примере ProxyImage является прокси-объектом для RealImage, который загружает изображение только при первом вызове метода display().
Последующие вызовы display() используют уже загруженное изображение без повторной загрузки с диска.*/

