# AtiperaApp

This project contains an API for retrieving Git repositories and their branches.
It provides an endpoint to fetch user repositories based on their GitHub username.
## Usage
Spring boot web/webflux/

## Installation
1. Clone this repository.
2. Make sure you have Java and Maven installed.
3. Build the project using Maven: `mvn clean install`.
4. Run the application: `mvn spring-boot:run`.
5. 
### GitEndpoint

- Endpoint: `/api/repositories/{username}`
- Method: GET
- Headers:
    - `Accept`: Response format (must be, `application/json`).
## Example
Assuming you have the application running locally,  you can retrieve repositories for a user by making a GET request to
http://localhost:8080/api/repositories/{username}
Replace `{username}` with the actual GitHub username.