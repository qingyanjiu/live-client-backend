# builder
FROM maven:3.5.2-jdk-8-alpine
COPY . /client
WORKDIR /client
RUN mvn package -Dmaven.test.skip=true

# publisher
FROM java:alpine
WORKDIR /web
COPY --from=0 /client/target/*.jar .
EXPOSE 8000
CMD java -jar -Dcustom.server.url=$CONFIG_SERVER_URL *.jar