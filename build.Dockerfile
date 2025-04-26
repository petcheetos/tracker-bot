
FROM maven:3.9.5-eclipse-temurin-21 AS builder

WORKDIR /workspace
COPY . .

RUN mvn -q -DskipTests package
