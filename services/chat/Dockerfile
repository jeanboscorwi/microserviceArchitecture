# package stage
# Use an official OpenJDK as a parent image
FROM adoptopenjdk:14-jre-hotspot
MAINTAINER Jeanboscorwi
#Create user (microservices) and group (microservices)
#/app is the home directory of our created user (where the jar will be copied)
#for security use a different user than root as when in the container pod, we can don't want to give root access to the whole system.
RUN groupadd -r microservices && useradd -r -g microservices -m -d /app microservices
# Don't run as root, but with application user
USER microservices
# Copy the application contents into the container at /app
COPY --chown=microservices:microservices target/chat-*.jar /app/app.jar
# Set the working directory to /app
WORKDIR /app
# Make port 8081 available to the world outside this container
EXPOSE 8081
# Run app.py when the container launches
CMD ["java", "-jar", "/app/app.jar"]
