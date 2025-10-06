package org.example.behavioral; 

import java.util.ArrayList;
import java.util.List;

/*    Паттерн Наблюдатель (Observer) относится к категории поведенческих паттернов проектирования и определяет
зависимость типа "один ко многим" между объектами таким образом, что при изменении состояния одного объекта
все зависящие от него объекты уведомляются об этом и автоматически обновляются.

    Структура паттерна:
1. Субъект (Subject):
-Хранит список наблюдателей и предоставляет методы для добавления и удаления наблюдателей.
-Уведомляет наблюдателей об изменениях своего состояния.
2. Наблюдатель (Observer):
-Определяет интерфейс для объектов, которые должны быть уведомлены об изменениях в субъекте.
3. Конкретный субъект (Concrete Subject):
-Хранит состояние, представляющее интерес для конкретных наблюдателей.
-Посылает уведомление своим наблюдателям при изменении состояния.
4. Конкретный наблюдатель (Concrete Observer):
-Реализует интерфейс Observer для поддержания согласованности с состоянием субъекта.

    Пример использования паттерна Наблюдатель:
Рассмотрим пример с метеостанцией, которая уведомляет различные дисплеи об изменении погодных условий.*/

// Интерфейс наблюдателя
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Интерфейс субъекта
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Конкретный субъект - Метеостанция
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered");
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed");
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    private void measurementsChanged() {
        notifyObservers();
    }
}

// Конкретный наблюдатель - Дисплей текущих условий
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity");
    }
}

// Конкретный наблюдатель - Статистический дисплей
class StatisticsDisplay implements Observer {
    private float maxTemp = 0.0f;
    private float minTemp = 200.0f;
    private float tempSum = 0.0f;
    private int numReadings = 0;

    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    public void display() {
        System.out.println("Avg/Max/Min temperature: " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}

// Конкретный наблюдатель - Прогноз
class ForecastDisplay implements Observer {
    private float currentPressure = 29.92f;
    private float lastPressure;

    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
}

// Пример использования
public class ObserverPattern {
    public static void main(String[] args) {
        // Создаем субъект
        WeatherStation weatherStation = new WeatherStation();

        // Создаем наблюдателей
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        // Регистрируем наблюдателей
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);
        weatherStation.registerObserver(forecastDisplay);

        // Обновляем данные метеостанции
        System.out.println("\n--- First measurements ---");
        weatherStation.setMeasurements(25.5f, 65.0f, 30.4f);

        System.out.println("\n--- Second measurements ---");
        weatherStation.setMeasurements(27.8f, 70.0f, 29.2f);

        System.out.println("\n--- Third measurements ---");
        weatherStation.setMeasurements(23.3f, 90.0f, 29.5f);
    }
}
/*
    Преимущества паттерна Наблюдатель:
1. Слабая связанность: Субъект и наблюдатели слабо связаны. Субъект знает только то, что у наблюдателя есть определенный интерфейс.
2. Поддержка широковещательных коммуникаций: Уведомление рассылается автоматически всем заинтересованным объектам.
3. Динамическое добавление/удаление: Можно добавлять и удалять наблюдателей в любое время.

    Недостатки паттерна Наблюдатель:
1. Случайные обновления: Наблюдатели не знают о присутствии друг друга и могут не подозревать о стоимости изменения субъекта.
2. Утечки памяти: Если наблюдатели не удаляются должным образом, это может привести к утечкам памяти.
3. Непредсказуемый порядок уведомлений: Порядок уведомления наблюдателей не гарантирован.*/

