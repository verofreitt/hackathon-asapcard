FROM maven:3-amazoncorretto-17

WORKDIR /app
COPY src/ /app/src
COPY pom.xml /app/pom.xml

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/ingestao-0.0.1-SNAPSHOT.jar"]
