# Реализация системы мониторинга с использованием Spring Kafka

## Содержание

1. [Общая архитектура системы](#общая-архитектура-системы)
2. [Конфигурация](#конфигурация)
    - [Конфигурационные файлы приложения](#конфигурационные-файлы-приложения)
3. [Сбор и отправка метрик](#сбор-и-отправка-метрик)
    - [Пример JSON метрики](#пример-json-метрики)
    - [Отправка метрик в Kafka](#отправка-метрик-в-kafka)
    - [Чтение из Kafka и обработка метрик](#чтение-из-kafka-и-обработка-метрик)
4. [Инструкции по запуску и использованию системы](#инструкции-по-запуску-и-использованию-системы)
    - [Запуск системы](#запуск-системы)
    - [Использование системы](#использование-системы)
       - [OpenAPI](#openapi)

## Общая архитектура системы

Система состоит из следующих компонентов:

- **Zookeeper:** используется для координации и управления конфигурацией Kafka.
- **Kafka:** система обмена сообщениями, используемая для передачи данных между компонентами.
- **Kafka UI:** интерфейс для управления и мониторинга Kafka.
- **PostgreSQL:** реляционная база данных для хранения данных системы.
- **PgAdmin:** инструмент для управления PostgreSQL.
- **producer-metrics:** приложение на Spring Boot для сбора метрик.
- **consumer-metrics:** приложение на Spring Boot для обработки метрик.

# Конфигурация

Конфигурация системы находится в файле `docker-compose.yml`, который определяет следующие сервисы:

- **zookeeper**: обеспечивает координацию для Kafka.
- **kafka**: основной брокер сообщений.
- **kafka-ui**: веб-интерфейс для управления Kafka.
- **postgres**: база данных для хранения метрик.
- **pgadmin**: веб-интерфейс для управления PostgreSQL.

## Конфигурационные файлы приложения

### application.yaml

Файл `application.yaml` содержит конфигурацию для Spring Boot приложения, включая настройки базы данных, JPA, и Kafka.

**Основные настройки:**

- `server.port`: порт, на котором работает приложение. `producer-metrics` приложение работает на порту 8080, `consumer-metrics` — на порту 8081.
- `spring.datasource`: параметры подключения к базе данных.
- `spring.cloud.stream`: настройки для взаимодействия с Kafka.

### application-docker.yaml

Файл `application-docker.yaml` используется для конфигурации базы данных при запуске в Docker.

**Основные настройки:**

- `spring.datasource`: параметры подключения к базе данных в Docker-среде.

# Сбор и отправка метрик

## Пример JSON метрики

Приложение `producer-metrics` собирает и отправляет метрики в формате JSON. Пример метрики:

```json
{
  "cpuUsage": "75%",
  "memoryUsage": "6144MB",
  "diskUsage": "180GB"
}
```
## Описание полей:

- **cpuUsage:** загрузка процессора в процентах.
- **memoryUsage:** использование оперативной памяти в мегабайтах.
- **diskUsage:** использование дискового пространства в гигабайтах.

## Отправка метрик в Kafka

 Микросервис `producer-metrics` собирает метрики и отправляет их в топик Kafka с именем `metrics-topic`.

## Чтение из Kafka и обработка метрик 

 Микросервис `consumer-metrics` читает метрики из топика Kafka  с именем `metrics-topic`. В случае ошибок, сообщения отправляются в *Dead Letter Queue (DLQ)* топик `messages-with-errors-consumer-metrics`.
 При успешноми чтении из топика сообщения сохраняются в базу данных.

### Контроллеры для анализа метрик

Приложение `consumer-metrics` предоставляет REST API для анализа метрик.

#### Получение списка всех метрик

```java
@Operation(summary = "Получение списка всех метрик производительности системы")
@GetMapping
public ResponseEntity<List<SystemPerformanceMetric>> getAllMetrics() {
    return ResponseEntity.ok(service.getAllMetrics());
}
```
#### Получение конкретной метрики по идентификатору

```java
@Operation(summary = "Получение конкретной метрики производительности системы по ее идентификатору")
@GetMapping("/{id}")
public ResponseEntity<SystemPerformanceMetricReadDto> getMetricById(@PathVariable Long id) {
    return service.getMetricById(id)
            .map(metric -> ResponseEntity.ok().body(metric))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
}
```

## Инструкции по запуску и использованию системы

### Запуск системы

1. Убедитесь, что у вас установлен Docker и Docker Compose.
2. В командной строке перейдите в директорию, где находится `docker-compose.yml`.
3. Запустите команду:
   ```bash
   docker-compose up -d
   ```
4. Проверьте, что все сервисы запущены:
    ```bash
    docker-compose ps
    ```

5. Запустите приложения **Spring Boot**

## Использование системы

- **Kafka UI**: доступ к интерфейсу Kafka UI можно получить по адресу [http://localhost:8182](http://localhost:8182).
- **PgAdmin**: доступ к интерфейсу PgAdmin можно получить по адресу [http://localhost:5050](http://localhost:5050).

- #### OpenAPI

- **Producer Metrics**: доступ к OpenAPI для producer-metrics можно получить по адресу [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- **Consumer Metrics**: доступ к OpenAPI для consumer-metrics можно получить по адресу [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html).
