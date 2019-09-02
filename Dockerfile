FROM openjdk:8-alpine

COPY target/uberjar/rentacarclojure.jar /rentacarclojure/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/rentacarclojure/app.jar"]
