FROM java:7
EXPOSE 8080
VOLUME /tmp
ADD eventos-api-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]