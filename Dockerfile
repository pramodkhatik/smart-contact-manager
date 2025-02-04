# Stage 1: Build Stage
FROM openjdk:17-jdk-alpine AS build

# Install Maven
ENV MAVEN_VERSION 3.8.8
ENV MAVEN_HOME /usr/lib/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}
RUN apk add --no-cache curl && \
    curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar xzf - -C /usr/lib && \
    ln -s /usr/lib/apache-maven-${MAVEN_VERSION} ${MAVEN_HOME}

# Set up the working directory
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies
# RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime Stage
FROM openjdk:17-jdk-alpine

# Set up the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/SmartContactManager*.jar ./SmartContactManager.jar

# Expose the default Spring Boot port
EXPOSE 8081

# Run the Spring Boot application
CMD ["java", "-jar", "SmartContactManager.jar"]
