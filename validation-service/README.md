# Validation-Service

## Steps to create a working native image

1. Build jar file with `mvn clean package`
1. Clear META-INF for native image: `rm src/main/resources/META-INF/native-image/*.json`
1. Start agent to collect information: `java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/validation-service-0.0.1-SNAPSHOT.jar`
1. Make calls to make sure to collect everything and not forget something: 
    ```
   curl -X POST -H "Content-Type: application/json" --data "{}" localhost:8082/validation/age
   curl -X POST -H "Content-Type: application/json" --data "{\"dateOfBirth\": \"2010-12-06\"}" localhost:8082/validation/age
   curl -X POST -H "Content-Type: application/json" --data "{\"dateOfBirth\": \"1990-12-06\"}" localhost:8082/validation/age
   ```
1. Stop agent to generate new META-INF files
1. Remove AggregatedMemoryPoolBean from `reflect-config.json`:
    ```
   {
     "name":"org.graalvm.compiler.hotspot.management.AggregatedMemoryPoolBean",
     "allPublicConstructors":true
   }
   ```
1. Build native image: `mvn -Pnative clean package`
1. Run native image: `./target/validation-service`