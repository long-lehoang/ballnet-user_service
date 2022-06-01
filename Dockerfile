FROM openjdk:17-alpine
MAINTAINER Long Le <long.bk.khmt@gmail.com>

COPY target/*.jar app.jar
CMD ["java","-jar","app.jar"]