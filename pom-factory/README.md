# Про паттерн Factory
**Factory** — паттерн для создания объектов. Он позволяет создавать объекты, не раскрывая логику создания.

##  Пример 1: Page Factor
Когда нужно создать экземпляр класса `LoginPage`, вместо new LoginPage() мы используем "фабричный" метод:

```java
PageFactory.initElements(driver, new LoginPage());
```

Для Selenide:
```java
Selenide.page(LoginPage.class);
```
Эти фабричные методы скрывают под капотом детали создания элементов.

## Что происходит под капотом методов
1. **Инициализация элементов** <br>
Когда мы помечаем поля в классе аннотациями: 
- Selenide: `@FindBy`, `@FindBys` (поиск по нескольким локаторам @FindBy, все локаторы должны совпасть) или `@FindAll`
- Appium: `@AndroidFindBy`, `@iOSXCUITFindBy`, `@AndroidFindBys`, `@iOSXCUITFindBys` и т.д.<br>

 При вызове:
```java
Selenide.page(LoginPage.class);
```
происходит следующее:
- Сканируются все поля в классе LoginPage, помеченные аннотациями.
- Для каждого такого поля создаётся proxy-объект (заглушка), которая знает, как найти элемент на странице.
- При первом обращении к элементу (`usernameField.setValue("user")`) — браузер ищет его по указанному локатору.

2. **Ленивая инициализация (Lazy Initialization)** <br>
   Page Factory создаёт элементы только тогда, когда они нужны, а не сразу при старте. Это экономит ресурсы и ускоряет выполнение тестов.

Пример:
```java
@FindBy(id = "username")
private SelenideElement usernameField;
```

Элемент найдётся только при вызове:
```java
usernameField.setValue("user");

```

3. **Централизованное управление локаторами** <br>
   Все локаторы сосредоточены в одном месте — в классе страницы. Если интерфейс изменился, достаточно обновить один файл, а не искать локаторы во всех тестах.

## Пример 2: Создание фабрикив коде.
В приложении есть разные виды транспорта. Мы хотим создавать объекты транспорта в зависимости от типа.
Для этого нам необходимо:

1. Создать интерфейс `Transport`:
```java
public interface Transport {
    void start();
}
```

2. Создать конкретные реализации `Car`, `Bus`:
```java
public class Car implements Transport {
    @Override
    public void start() {
        System.out.println("Car started");
    }
}

public class Bus implements Transport {
    @Override
    public void start() {
        System.out.println("Bus started");
    }
}
```

3. Создать Enum с типами транспорта:
```java
public enum TransportType {
    CAR, BUS;
}
```

4. Создать фабрику `TransportFactory`, которая будет возвращать объекты конкретного вида транспорта в зависимости от типа:
```java
public class TransportFactory {
    public static Transport getTransport(TransportType type) {
        switch (type) {
            case CAR:
                return new Car();
            case BUS:
                return new Bus();
            default:
                throw new IllegalArgumentException("Unknown transport type");
        }
    }
}
```

5. Использовать фабрику для создания объектов транспорта:
```java
public class App {
    public static void main(String[] args) {
        Transport car = TransportFactory.getTransport(TransportType.CAR);
        car.start();
```

# Задача: Протестировать функционал логина

![Страница логина](images/loginPage.png) 

# Решение без применения паттерна
[Решение без применения паттерна](src/test/java/login/LoginTest.java)

## ❌ Минусы:

- Тест "грязный" — содержит много локаторов.
- При изменении локатора нужно менять во всех тестах.
- Сложно поддерживать при росте количества тестов.

# Решение с применением паттернов Page Object Model + Page Factory
[Решение с применением паттерна](src/test/java/login/LoginTestWithPOMAndFactory.java)

## ✅ Плюсы:
- Логика работы со страницей вынесена в отдельный класс. 
- Тест стал читабельным и понятным. 
- Можно переиспользовать методы login() и verifyUserIsLoggedIn() в других тестах. 
- Легко обновлять локаторы — достаточно поменять их в одном месте.

# Где еще может пригодиться?
- Создание Factory с WebDriver для запуска тестов в разных браузерах.
- Для API тестов если нужно создать клиенты для отправки запросов в разных тестовых средах(dev, stage).
- Factory для загрузки различных конфигов, например из config.properties  `AppConfig config = ConfigFactory.loadConfig();`