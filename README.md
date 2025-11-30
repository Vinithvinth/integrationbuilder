# Dynamic API Integration Spring Boot Application

## Overview

This project is a **Spring Boot application** that fetches user data from multiple third-party APIs dynamically.  
API details and field mappings are stored in a database, allowing changes without redeploying the application.  

Currently, the application integrates with the Calendly API, but it is designed to easily extend to other APIs like Dropbox or Slack.


## Features

- **Dynamic API Configuration**: API URLs, request types, authentication, and field mappings are database-driven.
- **Generic API Caller**: A single method handles any API request (GET/POST), parses responses, and saves data.
- **Authentication Support**: Supports Bearer token authentication for APIs like Calendly.
- **Temporary User Storage**: Fetched user data is stored in an H2 database table (`User`).
- **Extensible Architecture**: Adding a new API requires only a new configuration entry in the database.


## Tech Stack

- Java 17+
- Spring Boot 3.2+
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- Lombok (optional)
- RestTemplate (for API calls)
- Jackson (for JSON parsing)



## Database Structure

### 1. API Configuration (`API_CONFIG`)

| Column           | Type   | Description |
|-----------------|--------|-------------|
| id               | Long   | Primary key |
| apiName          | String | Name of the API (e.g., Calendly) |
| apiUrl           | String | API endpoint URL |
| requestType      | String | HTTP method (GET/POST) |
| authType         | String | Authentication type (e.g., Bearer) |
| authToken        | String | API token or key |
| responseMapping  | String | Fields to extract from API response (e.g., name,email) |

### 2. User Table (`USER`)

| Column    | Type   | Description |
|-----------|--------|-------------|
| id        | Long   | Primary key |
| name      | String | User name |
| email     | String | User email |
| apiName   | String | Source API name |



