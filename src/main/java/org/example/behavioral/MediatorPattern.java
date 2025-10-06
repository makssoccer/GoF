package org.example.behavioral; 

import java.util.ArrayList;
import java.util.List;

/*    Паттерн Посредник (Mediator) относится к категории поведенческих паттернов проектирования и используется для
снижения связанности между объектами, позволяя им взаимодействовать через объект-посредник. Посредник инкапсулирует
взаимодействие множества объектов, что позволяет изменять это взаимодействие независимо от самих объектов.

    Структура паттерна:
1. Посредник (Mediator):
-Определяет интерфейс для взаимодействия с объектами Colleague.
2. Конкретный посредник (Concrete Mediator):
-Реализует интерфейс Mediator и координирует взаимодействие между объектами Colleague.
-Знает о всех участниках взаимодействия и управляет ими.
3. Коллега (Colleague):
-Каждый класс Colleague знает о своем объекте Mediator.
-Каждый объект Colleague обращается к посреднику, когда ему нужно связаться с другим объектом.

    Пример использования паттерна Посредник:
Рассмотрим пример с чатом, где пользователи общаются через посредника (чат-сервер).*/

// Интерфейс посредника
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Конкретный посредник - Чат
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // Сообщение не отправляется отправителю
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}

// Абстрактный коллега
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

// Конкретный коллега
class ConcreteUser extends User {
    public ConcreteUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    public void send(String message) {
        System.out.println(this.name + " sending: " + message);
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println(this.name + " received: " + message);
    }
}

// Пример использования
public class MediatorPattern {
    public static void main(String[] args) {
        // Создаем посредника
        ChatMediator chatRoom = new ChatRoom();

        // Создаем пользователей
        User user1 = new ConcreteUser(chatRoom, "Alice");
        User user2 = new ConcreteUser(chatRoom, "Bob");
        User user3 = new ConcreteUser(chatRoom, "Charlie");
        User user4 = new ConcreteUser(chatRoom, "Diana");

        // Добавляем пользователей в чат
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        chatRoom.addUser(user4);

        // Пользователи отправляют сообщения
        System.out.println("--- Chat Room Communication ---");
        user1.send("Hello everyone!");
        
        System.out.println();
        user2.send("Hi Alice!");
        
        System.out.println();
        user3.send("Good morning!");
    }
}
/*
    Преимущества паттерна Посредник:
1. Уменьшение связанности: Объекты не ссылаются друг на друга явно, что упрощает их повторное использование.
2. Централизация управления: Вся логика взаимодействия между объектами сосредоточена в одном месте.
3. Упрощение протоколов взаимодействия: Связи многие-ко-многим заменяются на связи один-ко-многим, что проще понимать и поддерживать.
4. Упрощение добавления новых коллег: Можно легко добавлять новые типы коллег без изменения существующих.

    Недостатки паттерна Посредник:
1. Сложность посредника: По мере роста системы посредник может стать очень сложным и трудным для понимания.
2. Единая точка отказа: Если посредник работает неправильно, это может повлиять на всю систему.
3. Нарушение принципа единственной ответственности: Посредник может взять на себя слишком много ответственности.*/

