
# Spring Boot Portfolio Project

This Spring Boot portfolio project demonstrates the usage of MongoDB and Spring Security for role-based authentication and authorization. It includes two collections: Trainer and Pokemon, and establishes a relationship between trainers and their owned Pokémon.

## Features

Before you start, ensure you have the following installed:

- MongoDB integration for data storage.
- Role-based authentication using Spring Security (USER and ADMIN roles).
- Authorization mechanisms for controlling access to resources.

## Technologies Used

- Spring Boot
- MongoDB
- Spring Security
- Java

## API Endpoints
### Read all Pokemons
- Endpoint: GET /api/pokemons

Response :
```
 {
        "id": "65c4e58e60528d798c847364",
        "name": "Pikachu",
        "level": 1,
        "createdAt": "1995-05-08T16:30:38.562",
        "updatedAt": "1995-05-08T16:30:38.562"
  }
```

### Create Pokemon (ADMIN role needed)
- Endpoint: POST /api/pokemons




Payload
```

{
    "name":"Coffee",
    "level":"1"
}


```
Response
```
{
        "id": "65c4e58e60528d798c847323",
        "name": "Coffee",
        "level": 1,
        "createdAt": "2004 -01-08T16:30:38.562",
        "updatedAt": "2004 -01-08T16:30:38.562"
  }

```
### Read Trainers (ADMIN role needed)
- Endpoint: GET /api/trainers
- Password is bcypted by Spring Security
- Lists also trainers owned pokemons

Response
```
{
        "id": "65ba1c9bc6ce845576c86031",
        "firstName": "Ash",
        "lastName": "Ketshum",
        "userName": "masterTrainer",
        "age": 12,
        "password": "$2a$10$k7ShH5xA4OMEdQig7zjd2.KmYvUnErT174VNXZEftEP2/GQYUikIG",
        "createdAt": "1997-04-31T12:10:35.266",
        "updatedAt": "1997-04-08T16:32:33.066",
        "pokemons": [
            {
                "id": "65c4e58e60528d798c847364",
                "name": "Pikachu",
                "level": 1,
                "createdAt": "1997-02-08T16:30:38.562",
                "updatedAt": "1997-02-08T16:30:38.562"
            }
        ],
        "roles": [
            "USER",
            "ADMIN"
        ]
    }

```
