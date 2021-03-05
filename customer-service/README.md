# Customer-Service

## Steps to create a working native image - OUT-DATED!!!

####These steps are no longer necessary after using different dependencies. Nevertheless these steps help if something gets wrong and the configuration files under `src/main/resources/META-INF/native-image/*.json` need to be adapted manually. In this case the agent can be used to generate the whole configuration files and afterwards the needed parts can be extracted.

1. Build jar file with `mvn clean package`
1. Clear META-INF for native image: `rm src/main/resources/META-INF/native-image/*.json`
1. Start validation service: `docker-compose up validation-service-native` (or alternatively run it in IntelliJ)
1. Start agent to collect information: `java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/customer-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres,postgres-local`
1. Make calls to make sure to collect everything and not forget something: 
    ```
   curl -X POST -H "content-Type: application/json" --data "{\"lastName\": \"Mustermann\", \"income\": 2500.0 }" localhost:8081/customer
   curl -X POST -H "content-Type: application/json" --data "{\"lastName\": \"Mustermann\", \"income\": 2500.0, \"dateOfBirth\": \"1990-12-06\"}" localhost:8081/customer
   curl localhost:8081/customer/1
   ```
1. Stop agent to generate new META-INF files
1. Remove AggregatedMemoryPoolBean from `reflect-config.json`:
    ```
   {
     "name":"org.graalvm.compiler.hotspot.management.AggregatedMemoryPoolBean",
     "allPublicConstructors":true
   }
   ```
1. Add additional information to `reflect-config.json`:
    ```
   {
     "name" : "java.util.concurrent.CopyOnWriteArrayList",
     "fields" : [
       { "name" : "lock", "allowWrite" : true }
     ]
   },
   {
     "name": "org.flywaydb.core.internal.logging.log4j2.Log4j2LogCreator",
     "methods":[{"name":"<init>","parameterTypes":[] }]
   }
   ```
1. Build native image: `mvn -Pnative clean package`
1. Run native image: `./target/customer-service --spring.profiles.active=postgres,postgres-local`