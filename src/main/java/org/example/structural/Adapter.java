package org.example.structural;

/*    Паттерн "Адаптер" (Adapter) относится к категории структурных паттернов проектирования и используется для соединения двух несовместимых интерфейсов.
Адаптер обеспечивает совместимость между классами, которые иначе не могли бы работать вместе из-за несовместимости интерфейсов.

    Структура паттерна:
1. Целевой интерфейс (Target):
-Представляет собой интерфейс, с которым работает клиентский код. Это интерфейс, который необходимо использовать, но он не совместим с текущими классами.
2. Адаптируемый класс (Adaptee):
-Представляет собой существующий класс или интерфейс, который несовместим с целевым интерфейсом, но который нужно использовать вместо него.
3. Адаптер (Adapter):
-Реализует целевой интерфейс и обеспечивает связь между целевым интерфейсом и адаптируемым классом. Адаптер преобразует вызовы методов целевого интерфейса в вызовы методов адаптируемого класса.
    Пример использования паттерна Адаптер:
Предположим, у нас есть класс, который предоставляет функциональность по расчету квадратного корня, но его интерфейс не совместим с нашим клиентским кодом.
Мы можем создать адаптер, который преобразует вызовы методов из нашего интерфейса в вызовы методов адаптируемого класса.*/

interface SquareRootCalculator {
    double calculateSquareRoot(double number);
}

// Адаптируемый класс
class ThirdPartyCalculator {
    double calculateRoot(double number) {
        return Math.sqrt(number);
    }
}

// Адаптер
class CalculatorAdapter implements SquareRootCalculator {
    private ThirdPartyCalculator calculator;

    public CalculatorAdapter(ThirdPartyCalculator calculator) {
        this.calculator = calculator;
    }

    public double calculateSquareRoot(double number) {
        // Преобразование вызова метода целевого интерфейса в вызов метода адаптируемого класса
        return calculator.calculateRoot(number);
    }
}

// Пример использования
public class Adapter {
    public static void main(String[] args) {
        // Создание адаптируемого объекта
        ThirdPartyCalculator thirdPartyCalculator = new ThirdPartyCalculator();

        // Создание адаптера
        SquareRootCalculator adapter = new CalculatorAdapter(thirdPartyCalculator);

        // Вызов метода через адаптер
        double result = adapter.calculateSquareRoot(16);
        System.out.println("Square root: " + result);
    }
}
/*
    В этом примере CalculatorAdapter является адаптером для ThirdPartyCalculator, который предоставляет функциональность по расчету квадратного корня.
Адаптер реализует интерфейс SquareRootCalculator и преобразует вызовы методов из этого интерфейса в вызовы методов calculateRoot() адаптируемого класса.
Таким образом, клиентский код может использовать интерфейс SquareRootCalculator, не зная о реализации ThirdPartyCalculator.*/

