# eventBasedArchitecture

## parent-project
mvn archetype:generate -DgroupId=org.athi -DartifactId=parent-project
## foundation
mvn archetype:generate -DgroupId=org.athi.eba -DartifactId=foundation -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false
## client
mvn archetype:generate -DgroupId=com.athi.eba -DartifactId=client -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false
## order
mvn archetype:generate -DgroupId=com.athi.eba -DartifactId=order -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false
## payment
mvn archetype:generate -DgroupId=com.athi.eba -DartifactId=payment -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.0 -DinteractiveMode=false


## Docker Builds
## event
docker build -t eba/event .
docker run -d -p 8083:8083 --name eba_event eba/event:latest

### notify
docker build -t eba/notify .
docker run -d -p 8088:8088 --name eba_notify eba/notify:latest
docker exec -it eba_notify bash


## run 
mvn spring-boot:run --spring.profiles.active=dev