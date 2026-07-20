# Фреймворк автоматизированного тестирования системы "QASE"

**GitHub:** https://github.com/rena-lisovska/Qase

**Веб-приложение:** https://app.qase.io

___

## 📚 Оглавление
- [Описание проекта](#-описание-проекта)
- [Технологический стек](#-технологический-стек)
- [Структура проекта](#-структура-проекта)
- [Архитектурные паттерны](#-архитектурные-паттерны)
  - [Page Object Model (POM)](#-page-object-model-pom)
  - [Chain of Invocations](#-chain-of-invocations)
  - [Builder](#-builder)
  - [Factory (Test Data Factory)](#-factory-test-data-factory)
  - [Page Element/Wrappers (под вопросом)](#-page-elementwrappers-пока-под-вопросом)
  - [Steps](#-steps)
  - [Loadable Page](#-loadable-page)
  - [Retry Mechanism](#-retry-mechanism)
- [Чек-лист API-тестирования](#-чек-лист-api-тестов)
  - [модуль "Project"](#модуль-project)
  - [модуль "Test Case"](#модуль-test-case)
  - [модуль "Test Suite"](#модуль-test-suite)
- [Конфигурация, запуск тестов, отчётность](#-конфигурация-запуск-тестов-отчётность)
- Чек-лист UI тестов
  - Модуль Projects
  - Модуль Project Settings
  - Модуль 
  - Модуль

- Чек-лист интеграционных тестов (UI + API)

___

## 📝 Описание проекта
**Система Qase (Qase.io)** — облачная система управления тестированием (TMS — Test Management System). 
Служит центральной платформой для команд разработки и контроля качества, где создаются, хранятся и систематизируются тест-кейсы, а также планируются проверки.

### Основные возможности Qase:
* **Управление тест-кейсами:** создание, структурирование и редактирование детальных шагов для проверки функционала, а также переиспользование одинаковых шагов в разных тестах.
* **Тест-раны и прогоны:** запуск тестовых циклов с отслеживанием прогресса команды и затраченного времени.
* **Автоматизация:** интеграция с библиотеками автотестов через API для автоматического сбора результатов в отчеты.
* **Интеграции:** синхронизация с популярными таск-трекерами (Jira, Asana, Redmine) и CI/CD системами для сквозной работы над багами и задачами.
* **Аналитика:** предоставление дашбордов с метриками покрытия тестами и историей прогонов.
___

## 🛠️ Технологический стек
| Категория          | Технология             | Версия              |
|--------------------|------------------------|---------------------|
| Язык               | Java                   | 17                  |
| Сборщик            | Maven                  | 3.5.5               |
| Тестовый фреймворк | TestNG                 | 7.12.0              |
| UI-тестирование    | Selenide               | 7.16.2              |
| API-тестирование   | RestAssured            | 6.0.0               |
| Логирование        | Log4j2                 | 2.26.0              |
| Генерация данных   | JavaFaker              | 1.0.2               |
| JSON mapping       | Jackson                | (через RestAssured) |
| JSON Schema validation | JSON Schema Validator  | 6.0.0           |
| Отчётность         | Allure                 | 2.24.0              |
| Утилиты            | Lombok                 | 1.18.46             |

___

## 📦 Структура проекта

_В РАЗРАБОТКЕ_


## 🏗️ Архитектурные паттерны
### ◉ Page Object Model (POM)
Каждая страница (или её логическая часть) описывается отдельным классом, который хранит локаторы элементов и методы для взаимодействия с ними. 
Такой подход сокращает дублирование кода и упрощает сопровождение автотестов при изменении пользовательского интерфейса.
- BasePage — служебный родительский класс для всех page-классов.
- LoginPage — страница авторизации в системе.
- ProjectsPage — страница со списком доступных проектов и интерфейсом по управлению ими.
- ProjectSettingsPage (вкладка General) — страница по изменению настроект конкретного проекта.
- ProjectPage — страница конкретного проекта и интерфейсом по управлению им.
- TrashBinPage — специальная «Корзина», куда временно помещаются удаленные тест-кейсы конкретного проекта.
- _добавить модальные окна отдельно_ 


### ◉ Chain of Invocations
Паттерн позволяет вызывать методы один за другим в виде единой цепочки. 
На базе Page Object Model (POM) этот шаблон делает код тестов максимально читаемым.


### ◉ Builder
Паттерн использован для удобного создания DTO-объектов в тестах.
Позволяет формировать необходимые данные только с заполнением требуемых полей и делает код тестов более читаемым.


### ◉ Factory (Test Data Factory)
Паттерн выбран для централизованного создания тестовых данных. 
Позволяет избежать дублирования кода и предоставляет готовые наборы данных для различных сценариев тестирования.


### ◉ Page Element/Wrappers (пока под вопросом)
Паттерн представляет собой набор классов-обёрток над стандартными UI-элементами, инкапсулирующих логику взаимодействия с ними. 
Это позволяет переиспользовать общий функционал элементов, уменьшить дублирование кода и сделать Page Object более компактными и читаемыми.
- Input
- ComboBox
- Checkbox
- TextArea
- Button
- Select


### ◉ Steps
Step-классы инкапсулируют последовательность действий и типовые сценарии взаимодействия с системой. 
Такой подход уменьшает дублирование кода, повышает читаемость тестов и упрощает сопровождение проекта.


### ◉ Loadable Page
Паттерн расширяет Page Object Model, наделяя каждый Page-класс ответственностью за проверку собственной готовности к работе.
Такой подход централизует логику ожиданий, снижает вероятность возникновения нестабильных тестов.


### ◉ Retry Mechanism
Паттерн предназначен для автоматического повторного выполнения теста или отдельной операции в случае возникновения временных ошибок, 
не связанных с дефектами приложения (например, нестабильности сети, задержек загрузки страницы или кратковременной недоступности сервиса). 

## 🧰 Конфигурация, запуск тестов, отчётность
### 1. Настройка config.properties
Перед запуском тестов необходимо заполнить файл `config.properties`, указав собственные учётные данные и параметры окружения.

**Путь к файлу:** `src/test/resources/config.properties`

### Описание параметров

| Параметр | Описание |
|----------|----------|
| `API.baseUri` | Базовый URL API Qase |
| `API.basePath` | Базовый путь API |
| `API.token` | API-токен пользователя Qase |
| `UI.baseUri` | URL веб-приложения Qase |
| `browser` | Браузер для запуска UI-тестов (`chrome`, `firefox`, `edge`) |
| `user` | Email пользователя Qase |
| `password` | Пароль пользователя Qase |

> **Важно:** перед запуском тестов замените все значения `[ENTER YOUR VALUE]` на собственные.

### 2. Запуск тестов
| XML-файл | Назначение                                                           |
|----------|----------------------------------------------------------------------|
| `FullTests.xml` | Полный набор UI и API тестов (по умолчанию используется в `pom.xml`) |
| `SmokeTests.xml` | Smoke-тесты                                                          |
| `RegressionTests.xml` | Регрессионное тестирование                                           |
| `UiTests.xml` | Все UI-тесты                                                         |
| `ApiTests.xml` | Все API-тесты                                                        |
| `CrossBrowser.xml` | Запуск UI-тестов в Chrome, Edge и Firefox                            |

```bash
# Полный набор UI и API тестов из FullTests.xml
mvn clean test
```
```bash
# Все UI-тесты
mvn clean test -DsuiteXmlFile=src/test/resources/UiTests.xml
```
```bash
# Все API-тесты
mvn clean test -DsuiteXmlFile=src/test/resources/ApiTests.xml
```
```bash
# Smoke тестирование
mvn clean test -DsuiteXmlFile=src/test/resources/SmokeTests.xml
```
```bash
# Regression тестирование
mvn clean test -DsuiteXmlFile=src/test/resources/RegressionTests.xml
```
```bash
# Cross-browser тестирование
mvn clean test -DsuiteXmlFile=src/test/resources/CrossBrowser.xml
```

> Для локального запуска используется браузер, указанный в параметре browser файла config.properties. Набор CrossBrowser.xml выполняет тесты последовательно в браузерах Chrome, Edge и Firefox.

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

> **Примечание:** результаты тестов сохраняются в директорию `allure-results`, а готовый отчёт — в `target/site/allure-maven-plugin`.

### 4. CI/CD

В проекте настроен GitHub Actions (`.github/workflows/githubActions.yml`), который:
- запускает автоматические тесты при `push` и `pull request` в ветки `master` и `feature/*`;
- поддерживает ручной запуск (`workflow_dispatch`);
- использует GitHub Secrets для хранения учётных данных;
- формирует Allure Report по результатам выполнения тестов;
- публикует отчёт на GitHub Pages.


## 📑 Чек-лист API тестов
### Модуль "Project"
| №         | Тест-кейс                                | Метод               | Эндпоинт | Группа | Статус реализации |
|-----------|------------------------------------------|---------------------|----------|--------|-------------------|
| API-01-01 | Create project with all fields           | POST                | `/project` | Positive | ✔️ Done |
| API-01-02 | Create project with only required fields | POST                | `/project` | Positive | ✔️ Done |
| API-01-03 | Create project without required fields   | POST                | `/project` | Negative | ✔️ Done |
| API-01-04 | Create project with empty body           | POST                | `/project` | Negative | ✔️ Done |
| API-01-05 | Get project by code                      | GET                | `/project/{code}` | Positive | ✔️ Done |
| API-01-06 | Get all projects                         | GET                | `/project` | Positive | ✔️ Done |
| API-01-07 | Delete project by code                   | DELETE                | `/project/{code}` | Positive | ✔️ Done |
| API-01-08 | CRUD: create, get and delete project     | POST / GET / DELETE | `/project` / `/project/{code}` | Positive | ✔️ Done |

### Модуль "Test Case"
| №         | Тест-кейс                                      | Метод                       | Эндпоинт | Группа | Статус реализации |
|-----------|------------------------------------------------|-----------------------------|----------|--------|-------------------|
| API-02-01 | Create test case                               | POST                        | `/case/{code}` | Positive | ✔️ Done |
| API-02-02 | Get test case by id                            | GET                         | `/case/{code}/{id}` | Positive | ✔️ Done |
| API-02-03 | Update test case                               | PATCH                       | `/case/{code}/{id}` | Positive | ✔️ Done |
| API-02-04 | Delete test case                               | DELETE                      | `/case/{code}/{id}` | Positive | ✔️ Done |
| API-02-05 | Verify deleted test case                       | GET                       | `/case/{code}/{id}` | Positive | ✔️ Done |
| API-02-06 | CRUD: create, get, update and delete test case | POST / GET / PATCH / DELETE | `/case/{code}` / `/case/{code}/{id}` | Positive | ✔️ Done |


### Модуль "Test Suite"
| №         | Тест-кейс                                  | Метод               | Эндпоинт | Группа | Статус реализации |
|-----------|--------------------------------------------|---------------------|----------|--------|-------------------|
| API-03-01 | Create test suite with all fields          | POST                | `/suite/{code}` | Positive | ✔️ Done |
| API-03-02 | Create test suite without required fields  | POST                | `/suite/{code}` | Negative | ✔️ Done |
| API-03-03 | Update test suite                          | PATCH                | `/suite/{code}/{id}` | Positive | ✔️ Done |
| API-03-04 | Update test suite with incorrect parameter | PATCH                | `/suite/{code}/{id}` | Negative | ✔️ Done |
| API-03-05 | Get test suite                             | GET                | `/suite/{code}/{id}` | Positive | ✔️ Done |
| API-03-06 | Get test suite by non-existent id           | GET                | `/suite/{code}/{id}` | Negative | ✔️ Done |
| API-03-07 | Delete test suite                          | DELETE                | `/suite/{code}/{id}` | Positive | ✔️ Done |
| API-03-08 | Delete test suite with incorrect parameter | DELETE                | `/suite/{code}/{id}` | Negative | ✔️ Done |
| API-03-09 | CRUD: create, get and delete test suite    | POST / GET / PATCH / DELETE | `/suite/{code}` / `/suite/{code}/{id}` | Positive | ✔️ Done |



## 📑 Чек-лист UI тестов
https://docs.google.com/spreadsheets/d/15SvADCKblyJ6mzb4Zyr5w_LS91qAwvFPD-CtWx0lsJ4/edit?gid=0#gid=0
- Модуль Projects
- Модуль Project Settings
- Модуль
- Модуль

## 📑 Чек-лист интеграционных тестов (UI + API)