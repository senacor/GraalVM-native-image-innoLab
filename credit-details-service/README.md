# `credit-details-service`

Getting details for a specific credit.

## Building the project

### Build and run with a local JDK

```sh
./gradlew run
```

### Build and run with Docker

Build:
```sh
./gradlew dockerBuild

# or with Native Image:
./gradlew dockerBuildNative
```

Run:
```sh
docker run -p 8080:8080 credit-details-service:latest
```

Refer also to the `docker-compose.yml` in the parent directory.

## Accessing the service

```sh
❯ http :8080/credit/123456
HTTP/1.1 200 OK
Content-Type: application/json
Date: Fri, 12 Mar 2021 13:36:14 GMT
connection: keep-alive
content-length: 94

{
    "amount": 123456.0,
    "end": "2020-12-31",
    "id": "123456",
    "interestRate": 1.0,
    "start": "2020-01-01"
}
```
