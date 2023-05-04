# Fetching latest version of Java
FROM openjdk:18
 
# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/notify-1.0-SNAPSHOT.jar /app

# Exposing port 8088
EXPOSE 8088

# Starting the application
CMD ["java", "-jar", "notify-1.0-SNAPSHOT.jar"]
