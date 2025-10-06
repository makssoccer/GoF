package org.example.structural;

/*    Паттерн Мост (Bridge) относится к категории структурных паттернов проектирования и используется для отделения абстракции
от ее реализации, чтобы они могли изменяться независимо. Мост позволяет избежать постоянной привязки абстракции к реализации
и дает возможность выбирать реализацию во время выполнения программы.

    Структура паттерна:
1. Абстракция (Abstraction):
-Определяет интерфейс абстракции и содержит ссылку на объект типа Implementor.
2. Расширенная абстракция (Refined Abstraction):
-Расширяет интерфейс, определенный Abstraction.
3. Реализатор (Implementor):
-Определяет интерфейс для классов реализации. Этот интерфейс не обязан точно соответствовать интерфейсу Abstraction,
они могут быть совершенно разными. Обычно интерфейс Implementor предоставляет только примитивные операции,
а Abstraction определяет операции более высокого уровня, основанные на этих примитивах.
4. Конкретная реализация (Concrete Implementor):
-Содержит конкретную реализацию интерфейса Implementor.

    Пример использования паттерна Мост:
Рассмотрим пример с устройствами и пультами управления. У нас есть различные устройства (телевизор, радио)
и различные пульты управления (обычный пульт, расширенный пульт).*/

// Интерфейс реализации устройства
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

// Конкретная реализация - Телевизор
class TV implements Device {
    private int volume = 10;

    public void turnOn() {
        System.out.println("TV: Turning on, current volume: " + volume);
    }

    public void turnOff() {
        System.out.println("TV: Turning off");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV: Setting volume to " + volume);
    }
}

// Конкретная реализация - Радио
class Radio implements Device {
    private int volume = 5;

    public void turnOn() {
        System.out.println("Radio: Turning on, current volume: " + volume);
    }

    public void turnOff() {
        System.out.println("Radio: Turning off");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Radio: Setting volume to " + volume);
    }
}

// Абстракция - Пульт управления
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        System.out.println("Remote: Toggling power");
        device.turnOn();
    }

    public abstract void setVolume(int volume);
}

// Расширенная абстракция - Обычный пульт
class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) {
        super(device);
    }

    public void setVolume(int volume) {
        System.out.println("BasicRemote: Setting volume");
        device.setVolume(volume);
    }
}

// Расширенная абстракция - Продвинутый пульт
class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void setVolume(int volume) {
        System.out.println("AdvancedRemote: Setting volume with extra features");
        device.setVolume(volume);
    }

    public void mute() {
        System.out.println("AdvancedRemote: Muting device");
        device.setVolume(0);
    }
}

// Пример использования
public class BridgePattern {
    public static void main(String[] args) {
        // Телевизор с обычным пультом
        Device tv = new TV();
        RemoteControl basicRemote = new BasicRemote(tv);
        basicRemote.togglePower();
        basicRemote.setVolume(15);

        System.out.println();

        // Радио с продвинутым пультом
        Device radio = new Radio();
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);
        advancedRemote.togglePower();
        advancedRemote.setVolume(20);
        advancedRemote.mute();
    }
}
/*
    Преимущества паттерна Мост:
1. Разделение абстракции и реализации: Паттерн позволяет изменять абстракцию и реализацию независимо друг от друга.
2. Уменьшение числа подклассов: Вместо создания множества подклассов для каждой комбинации абстракции и реализации,
паттерн Мост позволяет комбинировать их динамически.
3. Упрощение кода: Код становится более гибким и легким для понимания.
4. Соответствие принципу открытости/закрытости: Можно добавлять новые абстракции и реализации независимо.

    Недостатки паттерна Мост:
1. Усложнение кода: Паттерн может усложнить код из-за добавления дополнительных уровней абстракции.*/

