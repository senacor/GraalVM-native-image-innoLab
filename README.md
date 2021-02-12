# GraalVM-native-image-innoLab
https://github.com/senacor/GraalVM-native-image-innoLab/workflows/deploy-to-ebs/badge.svg
https://github.com/senacor/GraalVM-native-image-innoLab/workflows/credit-check-service-pipeline/badge.svg
https://github.com/senacor/GraalVM-native-image-innoLab/workflows/customer-service-pipeline/badge.svg
https://github.com/senacor/GraalVM-native-image-innoLab/workflows/validation-service-pipeline/badge.svg

## Start services running with JVM

1. Start docker compose via provided scripts `install-local.sh` for a jvm docker compose setup or 
   `install-local-native.sh` for a native image docker compose setup


## Start services running as native image

1. Make sure that docker has more than 12G memory (e.g. Docker Desktop -> Preferences -> Resources -> Memory) because otherwise the native image can not be build
1. Start docker compose with `docker-compose up`

## Short API description

## Validation Service API
The Validation Service validates the date of birth and denies it if the person is not over 18 years old.

Short description of the Validation Service API:

- Hello message: `curl localhost:8082`
- Validate date of birth: `curl -X POST -H "Content-Type: application/json" --data "{\"dateOfBirth\": \"2010-12-06\"}" localhost:8082/validation/age`

### Customer Services API
The Customer Service is used to create, read, update or delete customer and used the validation service to validate the date of birth of customers.

Short description of the Customer Service API:

- Hello message: `curl localhost:8081`
- Get all customer: `curl localhost:8081/customer`
- Create customer: `curl -X POST -H "content-Type: application/json" --data "{\"lastName\": \"Mustermann\", \"birthdate\": \"1990-12-06T12:00:00.000Z\"}" localhost:8081/customer`
- Get customer: `curl localhost:8081/customer/1`
- Update customer: `curl -X PUT -H "content-Type: application/json" --data "{\"firstName\": \"Max\"}" localhost:8081/customer/1`
- Delete customer: `curl -X DELETE localhost:8081/customer/1`


### Credit-check-service API

The credit check service has only 1 endpoint to check a credit http file specifying this endpoint can be found and executed
under `credit-check-service/requests/postCheckCredit.http` if executed against the deployed docker compose, the results can be viewed in the neo4j console
on `localhost:7474`

