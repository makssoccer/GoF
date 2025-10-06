package org.example.behavioral;
/*
    Паттерн Интерпретатор (Interpreter) относится к категории поведенческих паттернов проектирования и используется для интерпретации предложенного языка или грамматики.
Он определяет грамматику для представления языка и предоставляет интерпретатор, который интерпретирует предложения этого языка.

    Структура паттерна:
1. Абстрактное выражение (Abstract Expression):
-Определяет интерфейс для интерпретации контекста. Обычно содержит метод interpret(), который принимает контекст и возвращает результат интерпретации.
2. Терминальное выражение (Terminal Expression):
-Реализует интерфейс абстрактного выражения и представляет терминальный символ грамматики. Обычно каждый терминальный символ представлен одним терминальным выражением.
3. Нетерминальное выражение (Non-terminal Expression):
-Также реализует интерфейс абстрактного выражения, но представляет нетерминальный символ грамматики. Оно обычно состоит из одного или нескольких других выражений.
4. Контекст (Context):
-Содержит информацию, которая интерпретируется. Обычно используется клиентом для передачи данных в интерпретатор.
5. Интерпретатор (Interpreter):
-Интерпретирует предложения языка. Обычно содержит метод interpret(), который принимает контекст и вызывает методы интерпретации выражений.

    Пример использования паттерна Интерпретатор:
Рассмотрим пример с простым интерпретатором для вычисления арифметических выражений.*/

// Абстрактное выражение
interface Expression {
    int interpret(Context context);
}

// Терминальное выражение
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret(Context context) {
        return number;
    }
}

// Нетерминальное выражение
class AddExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AddExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret(Context context) {
        return leftExpression.interpret(context) + rightExpression.interpret(context);
    }
}

// Контекст
class Context {
    // Дополнительные данные, если необходимо
}

// Пример использования
public class InterpreterPattern {
    public static void main(String[] args) {
        // Создание выражений
        Expression expression = new AddExpression(new NumberExpression(10), new NumberExpression(5));

        // Создание контекста
        Context context = new Context();

        // Интерпретация выражения
        int result = expression.interpret(context);
        System.out.println("Result: " + result);
    }
}
/*  В этом примере NumberExpression и AddExpression представляют терминальные и нетерминальные выражения соответственно.
Интерпретатор использует контекст для передачи дополнительной информации, если это необходимо. Клиентский код создает выражения и контекст,
а затем вызывает метод interpret() для интерпретации выражения и получения результата.*/

