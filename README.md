# live-client-backend

mvn clean spring-boot:run -Drun.jvmArguments="-Dcustom.server.url=https://live-config.herokuapp.com/"

docker run -d -p xxxx:8000 -e CONFIG_SERVER_URL="https://live-config.herokuapp.com/" imagename
