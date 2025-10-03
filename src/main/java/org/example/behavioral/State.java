package org.example.behavioral; 

/*    Паттерн Состояние (State) относится к категории поведенческих паттернов проектирования и позволяет объекту
изменять свое поведение в зависимости от внутреннего состояния. При этом создается впечатление, что изменился класс объекта.

    Структура паттерна:
1. Контекст (Context):
-Определяет интерфейс, представляющий интерес для клиентов.
-Хранит ссылку на экземпляр подкласса State, определяющий текущее состояние.
2. Состояние (State):
-Определяет интерфейс для инкапсуляции поведения, ассоциированного с конкретным состоянием контекста.
3. Конкретное состояние (Concrete State):
-Каждый подкласс реализует поведение, ассоциированное с некоторым состоянием контекста.

    Пример использования паттерна Состояние:
Рассмотрим пример с торговым автоматом, который может находиться в разных состояниях.*/

// Интерфейс состояния
interface VendingMachineState {
    void insertCoin();
    void ejectCoin();
    void selectProduct();
    void dispense();
}

// Конкретное состояние - Нет монеты
class NoCoinState implements VendingMachineState {
    private VendingMachine machine;

    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Coin inserted");
        machine.setState(machine.getHasCoinState());
    }

    public void ejectCoin() {
        System.out.println("No coin to eject");
    }

    public void selectProduct() {
        System.out.println("Please insert coin first");
    }

    public void dispense() {
        System.out.println("Please insert coin first");
    }
}

// Конкретное состояние - Монета вставлена
class HasCoinState implements VendingMachineState {
    private VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    public void ejectCoin() {
        System.out.println("Coin ejected");
        machine.setState(machine.getNoCoinState());
    }

    public void selectProduct() {
        System.out.println("Product selected");
        machine.setState(machine.getDispensingState());
    }

    public void dispense() {
        System.out.println("Please select product first");
    }
}

// Конкретное состояние - Выдача товара
class DispensingState implements VendingMachineState {
    private VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Please wait, dispensing product");
    }

    public void ejectCoin() {
        System.out.println("Cannot eject coin, already dispensing");
    }

    public void selectProduct() {
        System.out.println("Product already selected");
    }

    public void dispense() {
        if (machine.getProductCount() > 0) {
            System.out.println("Dispensing product...");
            machine.releaseProduct();
            
            if (machine.getProductCount() > 0) {
                machine.setState(machine.getNoCoinState());
            } else {
                System.out.println("Out of products!");
                machine.setState(machine.getOutOfStockState());
            }
        }
    }
}

// Конкретное состояние - Товар закончился
class OutOfStockState implements VendingMachineState {
    public OutOfStockState(VendingMachine machine) {
        // Состояние не нуждается в ссылке на машину для текущей реализации
    }

    public void insertCoin() {
        System.out.println("Machine is out of stock. Coin ejected.");
    }

    public void ejectCoin() {
        System.out.println("No coin to eject");
    }

    public void selectProduct() {
        System.out.println("Machine is out of stock");
    }

    public void dispense() {
        System.out.println("Machine is out of stock");
    }
}

// Контекст - Торговый автомат
class VendingMachine {
    private VendingMachineState noCoinState;
    private VendingMachineState hasCoinState;
    private VendingMachineState dispensingState;
    private VendingMachineState outOfStockState;

    private VendingMachineState currentState;
    private int productCount;

    public VendingMachine(int productCount) {
        this.noCoinState = new NoCoinState(this);
        this.hasCoinState = new HasCoinState(this);
        this.dispensingState = new DispensingState(this);
        this.outOfStockState = new OutOfStockState(this);

        this.productCount = productCount;
        if (productCount > 0) {
            currentState = noCoinState;
        } else {
            currentState = outOfStockState;
        }
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void ejectCoin() {
        currentState.ejectCoin();
    }

    public void selectProduct() {
        currentState.selectProduct();
        currentState.dispense(); // Автоматически выдаем товар после выбора
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public void releaseProduct() {
        if (productCount > 0) {
            productCount--;
            System.out.println("Product released. Products remaining: " + productCount);
        }
    }

    public int getProductCount() {
        return productCount;
    }

    public VendingMachineState getNoCoinState() {
        return noCoinState;
    }

    public VendingMachineState getHasCoinState() {
        return hasCoinState;
    }

    public VendingMachineState getDispensingState() {
        return dispensingState;
    }

    public VendingMachineState getOutOfStockState() {
        return outOfStockState;
    }
}

// Пример использования
public class State {
    public static void main(String[] args) {
        System.out.println("=== Vending Machine with 3 products ===\n");
        VendingMachine machine = new VendingMachine(3);

        // Покупка первого товара
        System.out.println("--- Purchase 1 ---");
        machine.insertCoin();
        machine.selectProduct();

        // Покупка второго товара
        System.out.println("\n--- Purchase 2 ---");
        machine.insertCoin();
        machine.selectProduct();

        // Попытка выбрать товар без монеты
        System.out.println("\n--- Attempt without coin ---");
        machine.selectProduct();

        // Вставка и извлечение монеты
        System.out.println("\n--- Insert and eject coin ---");
        machine.insertCoin();
        machine.ejectCoin();

        // Покупка последнего товара
        System.out.println("\n--- Purchase 3 (last product) ---");
        machine.insertCoin();
        machine.selectProduct();

        // Попытка купить когда товар закончился
        System.out.println("\n--- Attempt when out of stock ---");
        machine.insertCoin();
        machine.selectProduct();
    }
}
/*
    Преимущества паттерна Состояние:
1. Локализация поведения: Поведение для каждого состояния собрано в одном месте.
2. Упрощение кода: Устраняет громоздкие условные операторы (if-else, switch-case).
3. Явные переходы: Переходы между состояниями становятся явными.
4. Разделение ответственности: Каждое состояние инкапсулировано в отдельном классе.
5. Легко добавлять новые состояния: Новые состояния можно добавлять без изменения существующих.

    Недостатки паттерна Состояние:
1. Увеличение количества классов: Каждое состояние требует отдельного класса.
2. Усложнение при малом количестве состояний: Может быть избыточным для простых случаев с 2-3 состояниями.*/

