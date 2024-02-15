# FruitBax
Java Spring boot CRUD restAPI with CI/CD and deployed on AWS
Made to meet the requirements in uppgiftsbeskrivning.pdf

## TLDR; Purpose of the assignment
Create a Java Spring boot rest API with crud functionality use CI/CD pipelines to deploy the application to AWS EC2.
For learning purposes, the repository should also use GitHub Actions for testing and packaging.

The client application is in a different repository. 

## Description of CI/CD pipeline

the default brach in the GitHub repository is 'dev' 


# API endpoints

## Ping endpoints

The Ping Controller is a simple controller used to check if the application is running. It has a single endpoint.

1. `GET /ping`
   - Description: Checks if the application is running.
   - Response: A string message "pong".
   - Status Codes: `200 OK` if the application is running.


## Fruit endpoints

Crud on a database with fruits

1. `GET /fruitbax/fruit`
    - Description: Fetches all fruits from the database.
    - Response: A list of `FruitDTO` objects. Each `FruitDTO` object contains the name of a fruit.
    - Status Codes: `200 OK` if fruits are found, `404 Not Found` if no fruits are found.

2. `GET /fruitbax/fruit/{fruitName}`
    - Description: Fetches a specific fruit by its name.
    - Path Variables: `fruitName` - The name of the fruit to fetch.
    - Response: A `FruitDTO` object containing the name of the fetched fruit.
    - Status Codes: `200 OK` if the fruit is found, `404 Not Found` if the fruit is not found.

3. `POST /fruitbax/fruit`
    - Description: Create a new fruit.
    - Request Body: A `FruitDTO` object containing the name of the fruit to be created.
    - Response: A `FruitDTO` object containing the name of the created fruit.
    - Status Codes: `200 OK` if the fruit is successfully created, `400 Bad Request` if the fruit could not be created.

4. `PUT /fruitbax/fruit`
    - Description: Updates the name of an existing fruit.
    - Request Body: An `UpdateFruitDTO` object containing the current name and the new name of the fruit.
    - Response: A `FruitDTO` object containing the name of the updated fruit.
    - Status Codes: `200 OK` if the fruit is successfully updated, `400 Bad Request` if the fruit could not be updated.

5. `DELETE /fruitbax/fruit`
    - Description: Deletes a specific fruit by its name.
    - Request Body: A `FruitDTO` object containing the name of the fruit to be deleted.
    - Response: No content.
    - Status Codes: `204 No Content` if the fruit is successfully deleted, `404 Not Found` if the fruit is not found.