# Fetching latest version of Java
FROM openjdk:18
 
# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/event-1.0-SNAPSHOT.jar /app

# Exposing port 8083
# EXPOSE 8083

# Starting the application
# CMD ["java", "-jar", "event-1.0-SNAPSHOT.jar"]

ENTRYPOINT ["java","-jar","event-1.0-SNAPSHOT.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 8083
