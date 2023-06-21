FROM openjdk:17-jdk-slim

# Install Maven
ENV MAVEN_VERSION 3.8.8
ENV MAVEN_HOME /usr/lib/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}
RUN apt-get update && \
    apt-get install -y curl && \
    curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar xzf - -C /usr/lib && \
    ln -s /usr/lib/apache-maven-${MAVEN_VERSION} ${MAVEN_HOME}


# Set up the working directory
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code
 COPY src /app/src

# Build the application
RUN mvn clean package

#Copy jar
COPY /target/SmartContactManager*.jar /app/SmartContactManager.jar

COPY . /app

# Expose the default Spring Boot port
EXPOSE 8081

# Run the Spring Boot application
CMD ["java", "-jar", "/app/SmartContactManager.jar"]
