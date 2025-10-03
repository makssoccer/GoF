package org.example.behavioral; 

/*   Шаблонный метод (Template Method) - это паттерн проектирования, который определяет скелет алгоритма в суперклассе,
оставляя реализацию некоторых шагов алгоритма подклассам. Таким образом, он позволяет подклассам переопределять конкретные
шаги алгоритма без изменения его общей структуры.
    Основные участники шаблона:
1)Абстрактный класс (AbstractClass): Определяет скелет алгоритма с использованием шаблонного метода.
Этот класс содержит один или несколько шаблонных методов, которые определяют общую структуру алгоритма, а также абстрактные методы,
которые должны быть реализованы в подклассах.
2)Конкретный класс (ConcreteClass): Реализует абстрактные методы, определенные в абстрактном классе. Эти методы представляют конкретные шаги алгоритма.*/

//    Пример
abstract class Algorithm {
    public void execute() {
        step1();
        step2();
        step3();
    }

    abstract void step1();

    abstract void step2();

    abstract void step3();
}

class ConcreteAlgorithm extends Algorithm {
    @Override
    void step1() {
        System.out.println("Step 1");
    }

    @Override
    void step2() {
        System.out.println("Step 2");
    }

    @Override
    void step3() {
        System.out.println("Step 3");
    }
}

public class Template_Method {
    public static void main(String[] args) {
        Algorithm algorithm = new ConcreteAlgorithm();
        algorithm.execute();
    }
