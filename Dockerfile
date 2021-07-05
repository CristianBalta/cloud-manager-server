FROM openjdk:15
COPY ./build/libs/cloud-manager-server.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=gcp", "-jar", "cloud-manager-server.jar"]