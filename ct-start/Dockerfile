# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
LABEL maintainer="hdthinh3105"

# Make port 5556 available to the world outside this container
EXPOSE 5555

# The application's jar file
ARG JAR_FILE=target/ct-start-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]