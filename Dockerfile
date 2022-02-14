FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/PhoneBookJumia.jar PhonBook-app.jar
ADD sample.db sample.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","/PhonBook-app.jar"]