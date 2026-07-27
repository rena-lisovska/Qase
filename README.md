# Фреймворк автоматизированного тестирования системы "QASE"

**GitHub:** https://github.com/rena-lisovska/Qase

**Веб-приложение:** https://app.qase.io

___

## 📚 Оглавление

- [Описание проекта](#-описание-проекта)
- [Технологический стек](#-технологический-стек)
- [Архитектура проекта](#-архитектура-проекта)
- [Паттерны проектирования](#-паттерны-проектирования)
    - [Page Object Model (POM)](#-page-object-model-pom)
    - [Chain of Invocations](#-chain-of-invocations)
    - [Builder и Test Data Factory](#-builder-и-factory-test-data-factory)
    - [Page Element/Wrappers](#-page-elementwrappers)
    - [Steps](#-steps)
    - [Loadable Page](#-loadable-page)
    - [Retry Mechanism](#-retry-mechanism)
    - [DataProvider](#-dataprovider)
- [Конфигурация, запуск тестов, отчётность](#-конфигурация-запуск-тестов-отчётность)
- [Чек-лист API-тестирования](#-чек-лист-api-тестов)
    - [модуль "Project"](#модуль-project)
    - [модуль "Test Case"](#модуль-test-case)
    - [модуль "Test Suite"](#модуль-test-suite)
- [Чек-лист UI тестов](#-чек-лист-ui-тестов)
    - [модуль "Authorization" (login)](#модуль-authorization)
    - [модуль "Projects"](#модуль-projects)
    - [модуль "Project Settings"](#модуль-project-settings)
    - [модуль "Project -> Test Suite"](#модуль-project---test-suite)

___

## 📝 Описание проекта

**Система Qase (Qase.io)** — облачная система управления тестированием (TMS — Test Management System).
Служит центральной платформой для команд разработки и контроля качества, где создаются, хранятся и систематизируются
тест-кейсы, а также планируются проверки.

___

## 🛠️ Технологический стек

| Категория              | Технология            | Версия              |
|------------------------|-----------------------|---------------------|
| Язык                   | Java                  | 17                  |
| Сборщик                | Maven                 | 3.5.5               |
| Тестовый фреймворк     | TestNG                | 7.12.0              |
| UI-тестирование        | Selenide              | 7.16.2              |
| API-тестирование       | RestAssured           | 6.0.0               |
| Логирование            | Log4j2                | 2.26.0              |
| Генерация данных       | JavaFaker             | 1.0.2               |
| JSON mapping           | Jackson               | (через RestAssured) |
| JSON Schema validation | JSON Schema Validator | 6.0.0               |
| Отчётность             | Allure                | 2.24.0              |
| Утилиты                | Lombok                | 1.18.46             |

___

## 📐 Архитектура проекта
Проект реализован с использованием многослойной архитектуры, что позволяет разделить ответственность между компонентами:

- **Tests** — содержат только сценарии тестирования и проверки.
- **Steps / Adapters** — инкапсулируют бизнес-логику.
- **Pages** — описывают пользовательский интерфейс.
- **Wrappers** — скрывают реализацию взаимодействия с элементами.
- **ApiClient** — единая точка выполнения HTTP-запросов.
- **Factories** — отвечают за генерацию тестовых данных.
- **Models (Request/Response)** — обеспечивают сериализацию и десериализацию данных.

``` text
Qase/
│
├── .github/                                                                   # Конфигурация GitHub
│   └── workflows/
│       └── gitHubActions.yml                                                  # CI/CD пайплайн для автоматического запуска тестов
│
├── pom.xml                                                                    # Конфигурация Maven и зависимости проекта
├── README.md                                                                  # Документация проекта
├── .gitignore                                                                 # Исключения Git
│
└── src/
    ├── main/
    │   └── java/
    │       │
    │       ├── api/                                                           # API-слой фреймворка
    │       │   ├── adapters/                                                  # Адаптеры фреймворка
    │       │   │   ├── BaseAdapter.java                                       # Базовые конфигурации адаптера фреймворка
    │       │   │   ├── ProjectAdapter.java                                    # Методы работы с Project API
    │       │   │   ├── TestCaseAdapter.java                                   # Методы работы с Test Case API
    │       │   │   └── TestSuiteAdapter.java                                  # Методы работы с Test Suite API
    │       │   │
    │       │   ├── client/
    │       │   │   └── ApiClient.java                                         # HTTP-клиент для выполнения GET/POST/PATCH/DELETE запросов
    │       │   │
    │       │   ├── endpoints/
    │       │   │   └── ApiEndpoints.java                                      # REST endpoint'ы приложения
    │       │   │
    │       │   └── models/                                                    # DTO модели API
    │       │       ├── project/                                               # Модели Project API
    │       │       │   ├── request/                                           # Тела запросов
    │       │       │   └── response/                                          # Модели ответов
    │       │       ├── testcase/                                              # Модели Test Case API
    │       │       │   ├── request/                                           # Тела запросов
    │       │       │   └── response/                                          # Модели ответов
    │       │       └── testsuite/                                             # Модели Test Suite API
    │       │           ├── request/                                           # Тела запросов
    │       │           └── response/                                          # Модели ответов
    │       │
    │       ├── core/                                                          # Общие компоненты фреймворка
    │       │   ├── enums/                                                     # Перечисления проекта
    │       │   ├── factory/                                                   # Генераторы тестовых данных
    │       │   │   ├── api/                                                   # Генерация API-моделей
    │       │   │   └── ui/                                                    # Генерация UI DTO
    │       │   └── utils/                                                     # Вспомогательные классы
    │       │       ├── PropertyReader.java                                    # Чтение конфигурации из properties
    │       │       └── SecureSelenide.java                                    # Безопасная работа с элементами Selenide
    │       │
    │       └── ui/                                                            # UI-слой фреймворка
    │           ├── dict/
    │           │   └── Elements.java                                          # Общие текстовые значения и элементы интерфейса
    │           │
    │           ├── dto/                                                       # DTO объекты для UI тестов
    │           │   ├── Project.java                                           # DTO модели Project
    │           │   └── Suite.java                                             # DTO модели Test Suite
    │           │
    │           ├── pages/                                                     # Page Object Model
    │           │   ├── BasePage.java                                          # Базовая страница
    │           │   ├── LoginPage.java                                         # Страница авторизации
    │           │   ├── ProjectsPage.java                                      # Страница проектов
    │           │   ├── ProjectPage.java                                       # Страница проекта
    │           │   ├── ProjectSettingsPage.java                               # Страница настроек проекта
    │           │   └── modals/                                                # Page Object модальных окон
    │           │       ├── CreateProjectModal.java                            # Модальное окно создания проекта
    │           │       ├── CreateSuiteModal.java                              # Модальное окно создания тест-сьюта
    │           │       └── ImportTestCasesModal.java                          # Модальное окно импорта тест-кейсов
    │           │
    │           ├── routes/
    │           │   └── UiRoutes.java                                          # URL маршруты приложения
    │           │
    │           ├── steps/                                                     # Бизнес-шаги UI тестов
    │           │   ├── LoginStep.java                                         # Шаги авторизации
    │           │   └── ProjectsStep.java                                      # Шаги работы с проектом
    │           │
    │           └── wrappers/                                                  # Обёртки над элементами интерфейса
    │               ├── ComboBox.java                                          # Выпадающий список
    │               ├── Input.java                                             # Поле ввода
    │               ├── RadioButton.java                                       # Радиокнопка
    │               └── TextArea.java                                          # Многострочное текстовое поле
    │
    └── test/
        ├── java/
        │   ├── core/                                                         # Инфраструктура тестового фреймворка
        │   │   ├── configs/                                                  
        │   │   │   ├── AllureConfig.java                                     # Настройка Allure-отчётности
        │   │   │   ├── BrowserConfig.java                                    # Настройка браузера
        │   │   │   └── TestConfig.java                                       # Общие настройки тестового окружения
        │   │   │
        │   │   ├── data/                                                     # Тестовые данные
        │   │   │   └── LoginTestData.java                                    # Данные для авторизации пользователей
        │   │   │
        │   │   ├── listeners/                                                # TestNG Listeners
        │   │   │   ├── AnnotationTransformer.java                            # Подключение Retry-аннотации ко всем тестам
        │   │   │   └── TestListener.java                                     # Логирование и обработка событий выполнения тестов
        │   │   │
        │   │   └── utils/                                                    # Вспомогательные классы для тестов
        │   │       ├── AllureUtils.java                                      # Добавление вложений и шагов в Allure
        │   │       └── Retry.java                                            # Повторный запуск упавших тестов
        │   │
        │   └── tests/                                                        # Наборы тестов
        │       ├── api/                                                      # API-тесты
        │       │   ├── CaseApiTest.java                                      # Tесты Test Cases
        │       │   ├── ProjectApiTest.java                                   # Tесты Projects
        │       │   └── SuiteApiTest.java                                     # Tесты Test Suites
        │       │
        │       └── ui/                                                       # UI-тесты
        │           ├── BaseTest.java                                         # Базовый класс UI-тестов
        │           ├── LoginUiTest.java                                      # Тесты авторизации
        │           ├── ProjectUiTest.java                                    # Тесты управления проектами
        │           ├── ProjectSettingsUiTest.java                            # Тесты настроек проекта
        │           └── SuiteUITest.java                                      # Тесты управления тест-сьютом     
        └── resources/
            ├── allure.properties                                             # Конфигурация Allure
            ├── config.properties                                             # Конфигурация тестового окружения
            ├── log4j2-test.xml                                               # Настройки логирования Log4j2
            │
            ├── schemas/                                                      # JSON Schema для валидации API-ответов
            │   ├── create_project_schema.json
            │   ├── delete_project_schema.json
            │   ├── get_all_projects_schema.json
            │   └── ...                                                       # Схемы ошибок и остальных ответов API
            │
            ├── suites/                                                       # XML-конфигурации TestNG
            │   ├── ApiTests.xml                                              # Запуск API-тестов
            │   ├── CrossBrowser.xml                                          # Кроссбраузерный запуск UI-тестов
            │   ├── FullTests.xml                                             # Полный запуск всех тестов
            │   ├── RegressionTests.xml                                       # Регрессионный набор
            │   ├── SmokeTests.xml                                            # Smoke-набор
            │   └── UiTests.xml                                               # Запуск UI-тестов
            │
            └── test-file/                                                    # Тестовые файлы для UI-тестов
               ├── logo/                                                     # Файлы для проверки загрузки логотипов
               └── suites/                                                   # Файлы для импорта Test Suites
```

### 🏗️ Слои фреймворка
Общая архитектура проекта.
```text
                      Tests
                        │
                        ▼
          Business Logic (Steps / Adapters)
                        │
                        ▼
                 Pages / API Client
                        │
                        ▼
                 Wrappers / REST API
                        │
                        ▼
                   Application
```

### 🖥️ Архитектура UI-автоматизации
Поток выполнения UI-тестов от запуска теста до взаимодействия с элементами интерфейса.
``` text
                    UI TEST

                        │
                        ▼
               LoginUiTest / ProjectUiTest
                        │
                        ▼
                 Step Layer (Business Logic)
          LoginStep / ProjectsStep
                        │
                        ▼
              Page Object Model (Pages)
 LoginPage → ProjectsPage → ProjectPage → Modals
                        │
                        ▼
               Wrappers (Reusable Elements)
      Input / ComboBox / RadioButton / TextArea
                        │
                        ▼
             Selenide + Selenium WebDriver
                        │
                        ▼
                    Qase Web UI
```

### 🌐 Архитектура API-автоматизации
Поток выполнения API-запросов внутри фреймворка.
``` text
                    API TEST

                        │
                        ▼
       ProjectApiTest / SuiteApiTest / CaseApiTest
                        │
                        ▼
                  Adapter Layer
 ProjectAdapter / TestSuiteAdapter / TestCaseAdapter
                        │
                        ▼
                     ApiClient
                        │
                        ▼
                  RestAssured Client
                        │
                        ▼
                    Qase REST API
                        │
                        ▼
               JSON Response ↔ DTO Models
```

### 📦 Поток данных тестирования
Схема формирования и использования тестовых данных.
```text
               Factory Classes
                     │
                     ▼
     Faker-generated Test Data / DTO Objects
                     │
                     ▼
         UI Tests              API Tests
             │                     │
             ▼                     ▼
      Pages / Steps          Request Models
```
___

## 🏗️ Паттерны проектирования

### ◉ Page Object Model (POM)

Каждая страница (или её логическая часть) описывается отдельным классом, который хранит локаторы элементов и методы для
взаимодействия с ними. Такой подход сокращает дублирование кода и упрощает сопровождение автотестов при изменении
пользовательского интерфейса.

- `BasePage` — служебный родительский класс для всех page-классов.
- `LoginPage` — страница авторизации в системе.
- `ProjectsPage` — страница со списком доступных проектов и интерфейсом по управлению ими.
- `ProjectSettingsPage` (вкладка General) — страница по изменению настроект конкретного проекта.
- `ProjectPage` — страница конкретного проекта и интерфейсом по управлению им.
- `CreateProjectModal` - модальное окно по созданию нового проекта.
- `CreateSuiteModal` - модальное окно по созданию нового тест-сьюта.
- `ImportTestCasesModal` - модальное окно по импорту тест-сьюта из внешних источников (через загрузку файла).

```java
public class LoginPage extends BasePage {

    private static final String LOGIN_INPUT = "[name=email]";
    private static final String PASSWORD_INPUT = "[name=password]";
    private static final String COOKIE_ACCEPT = "#accept";
    private static final String COOKIE_BANNER = "#usercentrics-cmp-ui";
    private static final String REQUIRED_FIELDS_MESSAGE = "//small[contains(text(), 'This field is required')]";
    private static final String NOT_MATCH_RECORDS_MESSAGE = "//div[@role='alert']//span[contains(text(), 'These credentials do not match our records')]";
    private static final String REMEMBER_ME_CHECKBOX = "input[name='remember']";
    private static final String REMEMBER_ME_CONTAINER = "[data-sentry-component='Checkbox']";

    @Override
    @Step("Open login page")
    public LoginPage openPage() {
        log.info("Opening login page");
        open(UiRoutes.LOGIN);
        acceptCookies();
        return this;
    }

    @Override
    @Step("Check that login page is opened")
    public LoginPage isPageOpened() {
        log.info("Checking that login page is open");
        webdriver().shouldHave(urlContaining(UiRoutes.LOGIN));
        $(byText(LOGIN_PAGE_TITLE))
                .shouldBe(visible
                        .because("Login page title should be displayed when login page is opened"));
        return this;
    }
}
```

### ◉ Chain of Invocations

Паттерн позволяет вызывать методы один за другим в виде единой цепочки.
На базе Page Object Model (POM) этот шаблон делает код тестов максимально читаемым.

```java

@Step("Create new project")
public ProjectPage createProject(Project project) {
    projectsPage.clickCreateProject();
    createProjectModal
            .isModalOpened()
            .fill(project)
            .clickCreate();
    return new ProjectPage().isPageOpened(project.getCode());
}
```

### ◉ Builder и Factory (Test Data Factory)

Паттерн Builder использован для удобного создания DTO-объектов в тестах. Позволяет формировать необходимые данные только
с заполнением требуемых полей и делает код тестов более читаемым.

Паттерн Factory выбран для централизованного создания тестовых данных. Позволяет избежать дублирования кода и
предоставляет готовые наборы данных для различных сценариев тестирования.

```java
    public static CreateProjectRequest validProjectRq() {
    CreateProjectRequest project = CreateProjectRequest.builder()
            .title(FAKER.company().name())
            .code(FAKER.bothify("QA##"))
            .description(FAKER.lorem().sentence())
            .access(AccessType.random().getValue())
            .group(GroupType.random().getValue())
            .build();
    log.info("Generated project with all fields: [{}]", project);
    return project;
}
```

### ◉ Page Element/Wrappers

Паттерн представляет собой набор классов-обёрток над стандартными UI-элементами, инкапсулирующих логику взаимодействия с
ними. Это позволяет переиспользовать общий функционал элементов, уменьшить дублирование кода и сделать Page Object более
компактными и читаемыми.

В проекте реализованы:

- `ComboBox`
- `Input`
- `RadioButton`
- `TextArea`

```java
private final Input projectName = new Input($("#project-name"), "Project name");
private final TextArea description = new TextArea($("#description-area"), "Description");
private final RadioButton privateAccess = new RadioButton($x("//input[@value='private']/ancestor::label"), "Private");
private final ComboBox chooseGroup = new ComboBox($x("//label[normalize-space()='Choose a group']/following::div[@role='combobox'][1]"), "Choose a group");
```

### ◉ Steps

Step-классы инкапсулируют последовательность действий и типовые сценарии взаимодействия с системой.
Такой подход уменьшает дублирование кода, повышает читаемость тестов и упрощает сопровождение проекта.

```java
    public void checkCreateProjectWithRequiredFields() {
    LoginTestData loginData = LoginTestData.validCredentials();
    project = UiProjectFactory.minimalProject();
    loginStep.authorize(
            loginData.getUsername(),
            loginData.getPassword()
    );
    projectStep.createProject(project)
            .verifyProjectName(project.getName());
}
```

### ◉ Loadable Page

Паттерн расширяет Page Object Model, наделяя каждый Page-класс ответственностью за проверку собственной готовности к
работе. Такой подход централизует логику ожиданий, снижает вероятность возникновения нестабильных тестов.

```java

@Override
@Step("Open projects page")
public ProjectsPage openPage() {
    log.info("Opening the Projects page");
    open(UiRoutes.PROJECTS);
    return this;
}

@Override
@Step("Check that projects page is opened")
public ProjectsPage isPageOpened() {
    log.info("Checking that projects page is opened");
    webdriver().shouldHave(urlContaining(UiRoutes.PROJECTS));
    $(byText(PROJECTS_PAGE_TITLE)).shouldBe(visible);
    return this;
}
```

### ◉ Retry Mechanism

Паттерн предназначен для автоматического повторного выполнения теста или отдельной операции в случае возникновения
временных ошибок, не связанных с дефектами приложения (например, нестабильности сети, задержек загрузки страницы или
кратковременной
недоступности сервиса).

```java
public class Retry implements IRetryAnalyzer {

    private int attempt = 1;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt < MAX_RETRY) {
                attempt++;
                iTestResult.setStatus(ITestResult.FAILURE);
                log.warn("Retrying once again");
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
```

```java
public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }
}
```

### ◉ DataProvider

Использование TestNG @DataProvider для параметризации тестов с различными наборами данных.

```java

@DataProvider(name = "invalidCredentials")
public Object[][] invalidCredentials() {
    return new Object[][]{
            {LoginTestData.invalidCredentialsWithUser()},
            {LoginTestData.invalidCredentialsWithPassword()}
    };
}
```
___

## 🧰 Конфигурация, запуск тестов, отчётность

### 1. Настройка config.properties

Перед запуском тестов необходимо заполнить файл `config.properties`, указав собственные учётные данные и параметры
окружения.

**Путь к файлу:** `src/test/resources/config.properties`

#### Описание параметров

| Параметр       | Описание                                                    |
|----------------|-------------------------------------------------------------|
| `API.baseUri`  | Базовый URL API Qase                                        |
| `API.basePath` | Базовый путь API                                            |
| `API.token`    | API-токен пользователя Qase                                 |
| `UI.baseUri`   | URL веб-приложения Qase                                     |
| `browser`      | Браузер для запуска UI-тестов (`chrome`, `firefox`, `edge`) |
| `user`         | Email пользователя Qase                                     |
| `password`     | Пароль пользователя Qase                                    |

> **Важно:** перед запуском тестов замените все значения `[ENTER YOUR VALUE]` на собственные.

### 2. Запуск тестов

| XML-файл              | Назначение                                                           |
|-----------------------|----------------------------------------------------------------------|
| `FullTests.xml`       | Полный набор UI и API тестов (по умолчанию используется в `pom.xml`) |
| `SmokeTests.xml`      | Smoke-тесты                                                          |
| `RegressionTests.xml` | Регрессионное тестирование                                           |
| `UiTests.xml`         | Все UI-тесты                                                         |
| `ApiTests.xml`        | Все API-тесты                                                        |
| `CrossBrowser.xml`    | Запуск UI-тестов в Chrome, Edge и Firefox                            |

```bash
# Полный набор UI и API тестов из FullTests.xml
mvn clean test
```

```bash
# Все UI-тесты
mvn clean test -DsuiteXmlFile=src/test/resources/suites/UiTests.xml
```

```bash
# Все API-тесты
mvn clean test -DsuiteXmlFile=src/test/resources/suites/ApiTests.xml
```

```bash
# Smoke тестирование
mvn clean test -DsuiteXmlFile=src/test/resources/suites/SmokeTests.xml
```

```bash
# Regression тестирование
mvn clean test -DsuiteXmlFile=src/test/resources/suites/RegressionTests.xml
```

```bash
# Cross-browser тестирование
mvn clean test -DsuiteXmlFile=src/test/resources/suites/CrossBrowser.xml
```

> Для локального запуска используется браузер, указанный в параметре browser файла config.properties. Набор
> CrossBrowser.xml выполняет тесты в браузерах Chrome, Edge и Firefox.

### 3. Отчётность

После выполнения тестов можно сформировать Allure-отчёт.

```bash
# Сгенерировать отчёт и автоматически открыть его в браузере
mvn allure:serve
```

```bash
# Только сгенерировать HTML-отчёт
mvn allure:report
```

> **Примечание:** результаты тестов сохраняются в директорию `allure-results`, а готовый отчёт — в
`target/site/allure-maven-plugin`.

### 4. CI/CD

В проекте настроен GitHub Actions (`.github/workflows/githubActions.yml`), который:

- запускает автоматические тесты при `push` и `pull request` в ветки `master` и `feature/*`;
- поддерживает ручной запуск (`workflow_dispatch`);
- использует GitHub Secrets для хранения учётных данных;
- формирует Allure Report по результатам выполнения тестов;
- публикует отчёт на GitHub Pages.
___

## 📑 Чек-лист API тестов

### Модуль "Project"

| №         | Тест-кейс                                | Метод               | Эндпоинт                       | Группа   | Статус реализации |
|-----------|------------------------------------------|---------------------|--------------------------------|----------|-------------------|
| API-01-01 | Create project with all fields           | POST                | `/project`                     | Positive | ✔️ Done           |
| API-01-02 | Create project with only required fields | POST                | `/project`                     | Positive | ✔️ Done           |
| API-01-03 | Create project without required fields   | POST                | `/project`                     | Negative | ✔️ Done           |
| API-01-04 | Create project with empty body           | POST                | `/project`                     | Negative | ✔️ Done           |
| API-01-05 | Get project by code                      | GET                 | `/project/{code}`              | Positive | ✔️ Done           |
| API-01-06 | Get all projects                         | GET                 | `/project`                     | Positive | ✔️ Done           |
| API-01-07 | Delete project by code                   | DELETE              | `/project/{code}`              | Positive | ✔️ Done           |
| API-01-08 | CRUD: create, get and delete project     | POST / GET / DELETE | `/project` / `/project/{code}` | Positive | ✔️ Done           |

### Модуль "Test Case"

| №         | Тест-кейс                                      | Метод                       | Эндпоинт                             | Группа   | Статус реализации |
|-----------|------------------------------------------------|-----------------------------|--------------------------------------|----------|-------------------|
| API-02-01 | Create test case                               | POST                        | `/case/{code}`                       | Positive | ✔️ Done           |
| API-02-02 | Get test case by id                            | GET                         | `/case/{code}/{id}`                  | Positive | ✔️ Done           |
| API-02-03 | Update test case                               | PATCH                       | `/case/{code}/{id}`                  | Positive | ✔️ Done           |
| API-02-04 | Delete test case                               | DELETE                      | `/case/{code}/{id}`                  | Positive | ✔️ Done           |
| API-02-05 | Verify deleted test case                       | GET                         | `/case/{code}/{id}`                  | Positive | ✔️ Done           |
| API-02-06 | CRUD: create, get, update and delete test case | POST / GET / PATCH / DELETE | `/case/{code}` / `/case/{code}/{id}` | Positive | ✔️ Done           |

### Модуль "Test Suite"

| №         | Тест-кейс                                  | Метод                       | Эндпоинт                               | Группа   | Статус реализации |
|-----------|--------------------------------------------|-----------------------------|----------------------------------------|----------|-------------------|
| API-03-01 | Create test suite with all fields          | POST                        | `/suite/{code}`                        | Positive | ✔️ Done           |
| API-03-02 | Create test suite without required fields  | POST                        | `/suite/{code}`                        | Negative | ✔️ Done           |
| API-03-03 | Update test suite                          | PATCH                       | `/suite/{code}/{id}`                   | Positive | ✔️ Done           |
| API-03-04 | Update test suite with incorrect parameter | PATCH                       | `/suite/{code}/{id}`                   | Negative | ✔️ Done           |
| API-03-05 | Get test suite                             | GET                         | `/suite/{code}/{id}`                   | Positive | ✔️ Done           |
| API-03-06 | Get test suite by non-existent id          | GET                         | `/suite/{code}/{id}`                   | Negative | ✔️ Done           |
| API-03-07 | Delete test suite                          | DELETE                      | `/suite/{code}/{id}`                   | Positive | ✔️ Done           |
| API-03-08 | Delete test suite with incorrect parameter | DELETE                      | `/suite/{code}/{id}`                   | Negative | ✔️ Done           |
| API-03-09 | CRUD: create, get and delete test suite    | POST / GET / PATCH / DELETE | `/suite/{code}` / `/suite/{code}/{id}` | Positive | ✔️ Done           |

___

## 📑 Чек-лист UI тестов

### Модуль "Authorization"

| №        | Тест-кейс                        | Группа   | Статус реализации |
|----------|----------------------------------|----------|-------------------|
| UI-01-01 | Login with valid credentials     | Positive | ✔️ Done           |
| UI-01-02 | Login with empty required fields | Negative | ✔️ Done           |
| UI-01-03 | Login with invalid credentials   | Negative | ✔️ Done           |
| UI-01-04 | Remember me checkbox state       | Positive | ✔️ Done           |

### Модуль "Projects"

| №        | Тест-кейс                              | Группа   | Статус реализации |
|----------|----------------------------------------|----------|-------------------|
| UI-02-01 | Create project with required fields    | Positive | ✔️ Done           |
| UI-02-02 | Create project with all fields         | Positive | ✔️ Done           |
| UI-02-03 | Create public project                  | Positive | ✔️ Done           |
| UI-02-04 | Create project with group access       | Positive | ✔️ Done           |
| UI-02-05 | Create project without members         | Positive | ✔️ Done           |
| UI-02-06 | Create project without name            | Negative | ✔️ Done           |
| UI-02-07 | Create project without code            | Negative | ✔️ Done           |
| UI-02-08 | Create project without group selection | Negative | ✔️ Done           |
| UI-02-09 | Create empty project                   | Negative | ✔️ Done           |
| UI-02-10 | Cancel project creation                | Negative | ✔️ Done           |
| UI-02-11 | Delete project                         | Positive | ✔️ Done           |

### Модуль "Project Settings"

| №        | Тест-кейс                          | Группа   | Статус реализации |
|----------|------------------------------------|----------|-------------------|
| UI-03-01 | Update project with new data       | Positive | ✔️ Done           |
| UI-03-02 | Append Update to project fields    | Positive | ✔️ Done           |
| UI-03-03 | Project name is required on update | Negative | ✔️ Done           |
| UI-03-04 | Project code is required on update | Negative | ✔️ Done           |
| UI-03-05 | Upload valid project logo          | Positive | ✔️ Done           |
| UI-03-06 | Upload invalid project logo        | Negative | ✔️ Done           |

### Модуль "Project -> Test Suite"

| №        | Тест-кейс                                        | Группа   | Статус реализации |
|----------|--------------------------------------------------|----------|-------------------|
| UI-04-01 | Create suite with all fields                     | Positive | ✔️ Done           |
| UI-04-02 | Cancel suite creation                            | Negative | ✔️ Done           |
| UI-04-03 | Import suite by valid file with .csv extension   | Positive | ✔️ Done           |
| UI-04-04 | Import suite by invalid file with .txt extension | Negative | ✔️ Done           |