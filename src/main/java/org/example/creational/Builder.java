package org.example.creational;

/*    Паттерн Строитель (Builder) относится к категории порождающих паттернов проектирования и используется для создания
сложных объектов пошагово. Builder позволяет использовать один и тот же код построения для получения разных представлений объектов.

    Структура паттерна:
1. Строитель (Builder):
-Определяет интерфейс для создания частей сложного объекта.
2. Конкретный строитель (Concrete Builder):
-Реализует интерфейс Builder и предоставляет конкретную реализацию шагов построения объекта.
Хранит создаваемый объект и предоставляет метод для получения готового продукта.
3. Продукт (Product):
-Представляет сложный объект, который создается. Конкретный строитель создает внутреннее представление продукта
и определяет процесс его сборки.
4. Директор (Director):
-Отвечает за управление процессом построения. Он знает, в какой последовательности нужно вызывать методы строителя
для создания продукта.

    Пример использования паттерна Строитель:
Рассмотрим пример создания объекта House (дом) с использованием паттерна Builder.*/

// Продукт
class House {
    private String foundation;
    private String structure;
    private String roof;
    private String interior;
    private boolean hasGarage;
    private boolean hasSwimmingPool;
    private boolean hasGarden;

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public void setHasSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", structure='" + structure + '\'' +
                ", roof='" + roof + '\'' +
                ", interior='" + interior + '\'' +
                ", hasGarage=" + hasGarage +
                ", hasSwimmingPool=" + hasSwimmingPool +
                ", hasGarden=" + hasGarden +
                '}';
    }
}

// Интерфейс строителя
interface HouseBuilder {
    void buildFoundation();
    void buildStructure();
    void buildRoof();
    void buildInterior();
    void buildGarage();
    void buildSwimmingPool();
    void buildGarden();
    House getHouse();
}

// Конкретный строитель - Обычный дом
class ConcreteHouseBuilder implements HouseBuilder {
    private House house;

    public ConcreteHouseBuilder() {
        this.house = new House();
    }

    public void buildFoundation() {
        house.setFoundation("Concrete foundation");
    }

    public void buildStructure() {
        house.setStructure("Concrete and brick structure");
    }

    public void buildRoof() {
        house.setRoof("Concrete roof");
    }

    public void buildInterior() {
        house.setInterior("Standard interior");
    }

    public void buildGarage() {
        house.setHasGarage(true);
    }

    public void buildSwimmingPool() {
        house.setHasSwimmingPool(false);
    }

    public void buildGarden() {
        house.setHasGarden(true);
    }

    public House getHouse() {
        return this.house;
    }
}

// Конкретный строитель - Вилла
class VillaBuilder implements HouseBuilder {
    private House house;

    public VillaBuilder() {
        this.house = new House();
    }

    public void buildFoundation() {
        house.setFoundation("Reinforced concrete foundation");
    }

    public void buildStructure() {
        house.setStructure("Premium structure with marble");
    }

    public void buildRoof() {
        house.setRoof("Spanish tile roof");
    }

    public void buildInterior() {
        house.setInterior("Luxury interior");
    }

    public void buildGarage() {
        house.setHasGarage(true);
    }

    public void buildSwimmingPool() {
        house.setHasSwimmingPool(true);
    }

    public void buildGarden() {
        house.setHasGarden(true);
    }

    public House getHouse() {
        return this.house;
    }
}

// Директор
class ConstructionDirector {
    private HouseBuilder builder;

    public ConstructionDirector(HouseBuilder builder) {
        this.builder = builder;
    }

    public void constructHouse() {
        builder.buildFoundation();
        builder.buildStructure();
        builder.buildRoof();
        builder.buildInterior();
    }

    public void constructFullFeaturedHouse() {
        builder.buildFoundation();
        builder.buildStructure();
        builder.buildRoof();
        builder.buildInterior();
        builder.buildGarage();
        builder.buildSwimmingPool();
        builder.buildGarden();
    }
}

// Пример использования
public class Builder {
    public static void main(String[] args) {
        // Создание обычного дома
        HouseBuilder concreteBuilder = new ConcreteHouseBuilder();
        ConstructionDirector director = new ConstructionDirector(concreteBuilder);
        director.constructHouse();
        House house = concreteBuilder.getHouse();
        System.out.println("Standard house: " + house);

        System.out.println();

        // Создание виллы со всеми удобствами
        HouseBuilder villaBuilder = new VillaBuilder();
        director = new ConstructionDirector(villaBuilder);
        director.constructFullFeaturedHouse();
        House villa = villaBuilder.getHouse();
        System.out.println("Luxury villa: " + villa);
    }
}
/*
    Преимущества паттерна Строитель:
1. Пошаговое создание объектов: Паттерн позволяет создавать объекты пошагово, что делает процесс создания более контролируемым.
2. Переиспользование кода: Один и тот же код построения можно использовать для создания различных представлений объектов.
3. Изоляция сложного кода: Сложный код построения изолирован от основной бизнес-логики продукта.
4. Создание различных представлений: Можно создавать различные представления объекта, используя один и тот же процесс построения.

    Недостатки паттерна Строитель:
1. Увеличение количества классов: Паттерн требует создания отдельного конкретного строителя для каждого типа продукта.
2. Усложнение кода: Для простых объектов использование паттерна может быть избыточным.*/

