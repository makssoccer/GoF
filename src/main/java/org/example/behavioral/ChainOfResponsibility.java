package org.example.behavioral;

/*    Паттерн Цепочка обязанностей (Chain of Responsibility) - это поведенческий паттерн проектирования,
который позволяет передавать запросы последовательно по цепочке обработчиков, пока один из них не обработает запрос.

    Основные участники шаблона:
1)Обработчик (Handler): Определяет интерфейс для обработки запроса и содержит ссылку на следующий обработчик в цепочке.
2)Конкретный обработчик (Concrete Handler): Реализует метод обработки запроса и решает, может ли он обработать запрос.
Если он не может обработать запрос, он передает его следующему обработчику в цепочке.
3)Клиент (Client): Создает и отправляет запросы цепочке обработчиков.

Пример:

Рассмотрим простой пример с использованием паттерна Цепочка обязанностей для обработки запросов на покупку товаров в интернет-магазине.*/

// Обработчик
abstract class PurchaseHandler {
    protected PurchaseHandler nextHandler;

    public void setNextHandler(PurchaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(PurchaseRequest request);
}

// Конкретные обработчики
class ManagerHandler extends PurchaseHandler {
    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() <= 1000) {
            System.out.println("Manager can approve purchase request: " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler can approve purchase request: " + request);
        }
    }
}

class DirectorHandler extends PurchaseHandler {
    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() <= 5000) {
            System.out.println("Director can approve purchase request: " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler can approve purchase request: " + request);
        }
    }
}

class CEOHandler extends PurchaseHandler {
    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() <= 10000) {
            System.out.println("CEO can approve purchase request: " + request);
        } else {
            System.out.println("No handler can approve purchase request: " + request);
        }
    }
}

// Запрос
class PurchaseRequest {
    private double amount;

    public PurchaseRequest(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "amount=" + amount +
                '}';
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        // Создаем цепочку обработчиков
        PurchaseHandler manager = new ManagerHandler();
        PurchaseHandler director = new DirectorHandler();
        PurchaseHandler ceo = new CEOHandler();

        manager.setNextHandler(director);
        director.setNextHandler(ceo);

        // Создаем запросы на покупку
        PurchaseRequest request1 = new PurchaseRequest(500);
        PurchaseRequest request2 = new PurchaseRequest(5000);
        PurchaseRequest request3 = new PurchaseRequest(15000);

        // Отправляем запросы на обработку
        manager.handleRequest(request1);
        manager.handleRequest(request2);
        manager.handleRequest(request3);
    }
}

/*В этом примере три конкретных обработчика (ManagerHandler, DirectorHandler, CEOHandler) обрабатывают запросы на покупку в зависимости от суммы.
Если текущий обработчик не может обработать запрос, он передает его следующему обработчику в цепочке.
Таким образом, запрос проходит по всей цепочке до тех пор, пока не будет обработан.*/

