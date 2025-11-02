# Weather Application

A weather application featuring selectable data sources: toggle between cache, PostgreSQL, or direct API calls to compare performance and testing strategies.

## Key Features & Benefits

*   **Multiple Data Sources:** Fetch weather data from cache, PostgreSQL database, or directly from a weather API.
*   **Performance Comparison:**  Easily switch between data sources to compare performance characteristics.
*   **Testing Strategies:** Experiment with different testing approaches based on the selected data source.
*   **Configurable Data Source:**  Select the data source at runtime (via configuration).
*   **REST API:** Provides a simple REST API to access weather information.

## Prerequisites & Dependencies

Before you begin, ensure you have met the following requirements:

*   **Java Development Kit (JDK):**  Version 11 or higher.
*   **Maven:**  Version 3.6.0 or higher.
*   **PostgreSQL (Optional):**  If you plan to use the PostgreSQL data source, you'll need a running PostgreSQL server and appropriate credentials.
*   **Internet Connection:** Required to fetch weather data directly from the API.

## Installation & Setup Instructions

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/qsheker/weather-app.git
    cd weather-app
    ```

2.  **Build the project using Maven:**

    ```bash
    ./mvnw clean install
    ```

3.  **Configure the application:**

    The application can be configured via the `application.properties` or `application.yml` file (located in `src/main/resources`).

    Example `application.properties` (using API as default source):

    ```properties
    spring.application.name=weather-app
    server.port=8080

    # Data Source Configuration
    weather.datasource=api  # Possible values: api, cache, postgres

    # PostgreSQL Configuration (if using postgres)
    spring.datasource.url=jdbc:postgresql://localhost:5432/weatherdb
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update # Or create, validate, none

    #API Key
    weather.api.key=YOUR_API_KEY  #Required for API usage
    ```

4.  **Run the application:**

    ```bash
    ./mvnw spring-boot:run
    ```

    Or, you can package it as a JAR and run it:

    ```bash
    java -jar target/weather-app-*.jar
    ```

## Usage Examples & API Documentation

The application exposes a REST API for accessing weather information.

### API Endpoints:

*   **`GET /weather/{city}`:**  Retrieves weather information for a given city.

    *   Example: `GET /weather/London`

    *   Response:

        ```json
        {
          "city": "London",
          "temperature": 15,
          "condition": "Cloudy",
          "dataSource": "api"
        }
        ```

### Example using `curl`:

```bash
curl http://localhost:8080/weather/London
```

## Configuration Options

The following configuration options are available:

| Property              | Description                                                                                                                                   | Default Value |
| --------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- | ------------- |
| `weather.datasource`    | Specifies the data source to use.  Possible values: `api`, `cache`, `postgres`.                                                         | `api`         |
| `spring.datasource.url` | JDBC URL for the PostgreSQL database (required if `weather.datasource` is set to `postgres`).                                             |               |
| `spring.datasource.username` | Username for the PostgreSQL database.                                                                                                          |               |
| `spring.datasource.password` | Password for the PostgreSQL database.                                                                                                          |               |
| `spring.jpa.hibernate.ddl-auto` | Strategy for database schema generation (e.g., `update`, `create`, `validate`, `none`).                                                        |               |
| `weather.api.key`    | The API Key used to access the Weather API.  This is REQUIRED if `weather.datasource` is `api`. Get your own API key from the API provider. |               |

## Contributing Guidelines

We welcome contributions to the Weather Application! To contribute:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Implement your changes.
4.  Write tests to ensure your changes work correctly.
5.  Submit a pull request.

Please ensure your code adheres to the project's coding style and includes appropriate documentation.

## License Information

This project is licensed under [insert license here - e.g., MIT License].

## Acknowledgments

*   [Mention any third-party libraries, APIs, or resources used in the project here]
