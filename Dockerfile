FROM openjdk:latest
ADD target/firstAssigmentDept-0.0.1-SNAPSHOT.jar firstAssigmentDept-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","firstAssigmentDept-0.0.1-SNAPSHOT.jar"]