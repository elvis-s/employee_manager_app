FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn package -DskipTests

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build /app/target/employee-management-app.0.0.1-SNAPSHOT.jar employee-management-app.jar 

EXPOSE 8080 

ENTRYPOINT ["java", "jar", "employee-management-app.jar"]
