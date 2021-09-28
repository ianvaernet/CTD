FROM maven
COPY pom.xml /user/local/services/pom.xml
COPY src /user/local/services/src
WORKDIR /user/local/services
RUN mvn package
CMD ["java", "-cp", "target/TFI-1.0.0.jar", "com.example.TFI.TfiApplication.main"]