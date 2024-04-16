# Jobs Portal Service 💼💵

This is the second microservice of the Jobs Portal system, developed using Quarkus.

## Features:

* RPC endpoints for creating profiles for Users, Company, and Admin.
* RPC endpoints for Create, Read, Update, and Delete operations on profiles.

For a detailed overview of how this service interacts with others, please refer to [this diagram](https://i.imgur.com/SECOND_MICROSERVICE_DIAGRAM.png).

## Additional Components:
* Integration with ActiveMQ for messaging.
* PostgreSQL database running on port 5432.
* ActiveMQ's user-friendly interface can be accessed at port 8161.

## How to Use:

1. Ensure that Docker and Docker Compose are installed on your system.
2. Clone this repository to your local machine.
3. Navigate to the root directory of the cloned repository.
4. Run `docker-compose up` to start the microservice and its dependencies.
5. Once the service is up and running, you can access the RPC endpoints.
6. Access the Quarkus developer console at [localhost:8080/q/dev](localhost:8080/q/dev).
