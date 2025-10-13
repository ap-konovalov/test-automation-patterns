# Про паттерн Dependency Injection

**Dependency Injection** - это паттерн проектирования, который позволяет "вкладывать" (внедрять) зависимости в объект извне, а не создавать
их внутри самого объекта.<br>
Проще говоря: вместо того чтобы внутри теста создавать нужные классы (например, клиент для API или Page Object), мы передаём их в тест уже
готовыми. Это делает код чище, гибче и легче для поддержки, а также позволяет легко подменять реальные зависимости моками или
заглушками.<br>
Было так (ApiClient создается внутри):

```java
public class PetHardcodedApiClient {

    private ApiClient apiClient = new RestAssuredApiClient(BASE_URI);

    public Pet postPet(Pet requestBody) {
        return apiClient.post(POST_PET, requestBody, Pet.class);
    }
}
```

Стало так (ApiClient создается снаружи и передается в конструкторе):

```java
public class PetDiApiClient {

    private static ApiClient apiClient;

    public PetDiApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Pet postPet(Pet requestBody) {
        return apiClient.post(POST_PET, requestBody, Pet.class);
    }
}
```

Переход от «создания объектов внутри» к «созданию снаружи» называется инверсией контроля (inversion of control). Пока объекты создавались
внутри, мы их контролировали сами. Теперь же кто-то снаружи создает и передает их внутрь готовыми — мы «инвертировали контроль».
А сам процесс создания снаружи и передачи внутрь — это внедрение зависимости (dependency injection).

## Когда использовать?

Когда проект растёт и начинаю появляться следующие проблемы:

- много зависимостей, и они вложены друг в друга (например, Service → Repository → HttpClient).
- нужно централизованно управлять конфигурацией зависимостей (например, менять реализацию ApiClient на мок).
- нужно переиспользовать зависимости между разными тестами, не дублируя код.
- нужно легко внедрять зависимости в разные классы без ручного связывания.
- нужно выстроить масштабируемую архитектуру тестов (например, для большого API-проекта с десятками сервисов).

# Задача

Необходимо написать автотесты на REST API интернет магазина животных. Конкретно на методы POST /add, POST /store/order
из [документации по ссылке](https://petstore.swagger.io/#/pet/addPet). Метод POST /store/order нужен чтобы разместить заказ на покупку
животного. Чтобы вызвать этот метод, нужно сначала создать животное, вызвав метод POST /add.

# Решение без применения паттерна

[PostPetTests.java](src/test/java/hardcoded_tests/PostPetTests.java)<br>
[PostStoreOrderTests.java](src/test/java/hardcoded_tests/PostStoreOrderTests.java)

## ❌ Минусы:

- Сложно управлять зависимостями между сервисами.
- Трудно подменять реализации (например, ApiClient → MockApiClient).
- Невозможно гибко конфигурировать окружения.
- Дублирование кода в каждом тесте (создание объектов ApiClient для внедрения в PetApiClient)
- Если создание объекта ApiClient происходит внутри PetApiClient/StoreApiClient и других клиентов, где нужен ApiClient - в памяти будет
  создано несколько экземпляров ApiClient, а для работы нам достаточно было бы одного.

# Как реализовать паттерн

1) Вручную вынести создание объекта из класса наружу (ApiClient создается снаружи и передается в конструкторе):

```java
public class PetDiApiClient {

    private static ApiClient apiClient;

    public PetDiApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Pet postPet(Pet requestBody) {
        return apiClient.post(POST_PET, requestBody, Pet.class);
    }
}
```

2) Использовать готовые решения, подключив к проекту Spring/Picocontainer/Guice и использовать их для внедрения зависимостей.<br>
   Эти фреймворки реализуют DI-контейнер (Dependency Injection Container) — это «умная коробка», которая помогает автоматически создавать
   объекты и передавать (внедрять) в них нужные зависимости.<br>
   DI-контейнер берёт на себя работу по:

- созданию объектов,
- настройке их зависимостей (других объектов),
- управлению их жизненным циклом (например, создавать контейнеры один раз для всех тестов или каждый раз заново).

## Когда можно обойтись без DI-контейнеров?

Когда проект небольшой, и зависимости простые — использовать DI-контейнер в тестах избыточно. Лучше использовать простую инициализацию или
базовый класс BaseTest.

| Подход              | Когда использовать                                                                     |
|---------------------|----------------------------------------------------------------------------------------| 
| Простой конструктор | Маленькие проекты, простые зависимости (ручное создание зависимостей в каждом тесте)   | 
| BaseTest            | Общие зависимости для всех тестов (центральный класс для инициализации общих объектов) | 
| DI-контейнер        | Сложные зависимости, нужна масштабируемость, нужно переиспользование                   |

# Решение с применением паттерна

Подключим к проекту библиотеку PicoContainer:

```java
    implementation("org.picocontainer:picocontainer:2.15.2")
```

Создадим DI-контейнер [BaseTest.java](src/test/java/di_tests/BaseTest.java) <br>
Используем DI-контейнер в тестах:<br>
[PostPetTests.java](src/test/java/di_tests/PostPetTests.java)<br>
[PostStoreOrderTests.java](src/test/java/di_tests/PostStoreOrderTests.java)

## ✅ Плюсы:

- Централизованное управление зависимостями. Все зависимости регистрируются в одном месте (BaseTest или отдельном DI-модуле).
- Лёгкая подмена реализаций. Можно легко заменить ApiClient на мок или другую реализацию без переписывания тестов.
- Автоматическое внедрение зависимостей. Не нужно вручную передавать зависимости в каждый сервис.
- Повторное использование компонентов. Один и тот же ApiClient используется во всех сервисах.
- Чистый и читаемый код тестов. Тесты фокусируются на логике, а не на инициализации зависимостей.
- Гибкая конфигурация окружений. Можно внедрять разные сервисы в зависимости от окружения (dev/stage/prod).

# Что почитать

https://habr.com/ru/companies/kaspersky/articles/757980/<br>
https://habr.com/ru/articles/350068/<br>
http://picocontainer.com/


# Бонус: как аннотации Spring связаны с DI?
При запуске приложения Spring сканирует классы и ищет в них аннотации:
- @Component
- @Service
- @Repository
- @Controller

Эти аннотации помечают классы как управляемые Spring контейнером (это DI-контейнер с расширенными возможностями). Когда Spring контейнер 
запускается, он создает экземпляры этих классов и внедряет зависимости, указанные в аннотациях, например, с помощью @Autowired<br>
Пример:
```java
@Component
public class ApiClient {
    // Spring сам создаст объект и будет им управлять
}
```

При запуске приложения Spring создаст экземпляр ApiClient. Созданный экземпляр также называют бином.<br>
Далее находится аннотация **@Autowired** - которая говорит Spring что нужно найти подходящий бин (созданный ранее объект, управляемый 
Spring) и 
подставить его в конструктор или сеттер.<br>
Пример:
```java
@Component
public class UserService {

    private final ApiClient apiClient;

    @Autowired
    public UserService(ApiClient apiClient) {
        this.apiClient = apiClient; // Spring сам подставит нужный бин
    }
}
```
✅ Нам не нужно нигде писать `UserService service = new UserService(new ApiClient());` так как Spring при старте приложения уже создал все 
бин(экземпляр класса ApiClient) и внедрил зависимости(прокинул ApiClient в конструктор UserService). Мы просто получаем бин через метод 
`@Autowired` или через конструктор.<br> 

**@Lazy** - позволяет отложить создание бина до первого использования. Это может быть полезно для бинов, которые требуют много ресурсов или 
 могут быть не нужны в некоторых случаях.
```java
@Component
@Lazy
public class HeavyService {
    public HeavyService() {
        System.out.println("HeavyService создан");
    }
}
```
Теперь HeavyService будет создан только тогда, когда он впервые понадобится, а не при старте приложения. Что ускорит запуск приложения.<br>

**@Configuration** - помечает класс как конфигурационный — в нём можно описывать, какие бины создавать вручную с помощью @Bean.<br>

**@Bean** - Используется внутри класса с @Configuration. Аннотация ставится над методом, возвращающий объект. Spring вызывает метод и сохраняет 
результат как бин для дальнейшего использования.
```java
@Configuration
public class AppConfig {

    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }
}
```
Теперь ApiClient можно внедрить через @Autowired.<br>

**@PostConstruct** - ставится над меотдом. Указывает, что метод должен быть выполнен сразу после того, как Spring создал бин и внедрил в 
него 
все зависимости.
```java
@Component
public class UserService {

    private final ApiClient apiClient;

    @Autowired
    public UserService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("UserService готов к работе");
        apiClient.sendRequest();
    }
}
```
Как отработает код:
- Шаг 1: Создастся ApiClient 
- Шаг 2: Создастся UserService 
- Шаг 3: Spring внедрит ApiClient в UserService 
- Шаг 3: Выполнится метод afterInit()