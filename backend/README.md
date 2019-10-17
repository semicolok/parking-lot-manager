# parking-lot-manager-backend(Seoul)
#### Set environment variables(Required)  
Environment variables are used in the project (application-{profile}.yml).  
You cannot skip this step.  
You can modify them, if you need.  
The project has 3 types of profiles. (dev, stag, prod)  
Use "dev" profile for testing.  
```  
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
$ export SPRING_PROFILES_ACTIVE=dev
$ export SEOUL_OPEN_API_BASE_URL=http://openapi.seoul.go.kr:8088
$ export SEOUL_OPEN_API_AUTH_KEY={your-auth-key}
$ export DATABASE_URL=jdbc:h2:~/test.h2;FILE_LOCK=SOCKET
$ export DATABASE_USERNAME=sa
$ export DATABASE_PASSWORD=
```  
#### Build project  
This project has gradle wrapper. You can build it without installing gradle.  
```  
$ ../gradlew :backend:clean :backend:build && ls -al build
```  
#### Build docker image  
```  
$ ../gradlew :backend:clean :backend:dockerBuildImage
```  
#### generate Dockerfile  
```  
$ ../gradlew :backend:dockerCreateDockerfile && cat build/docker/Dockerfile
```  
#### Run  
There are 2 ways you can run this project with. Run JAR file and use gradle "bootRun" task.  
##### jar run  
```  
$ java -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar
```  
##### gradle bootRun  
```  
$ ../gradlew :backend:clean :backend:bootRun
```  
#### Swagger url  
http://localhost:8080/swagger-ui.html  
http://localhost:8080/v2/api-docs  
#### example url  
search all: http://localhost:8080/search  
search with pagination parameters: http://localhost:8080/search?page=0&size=10  
search with address: http://localhost:8080/search?address=%EC%B2%AD%EB%8B%B4&page=0&size=10  
search with address and sort: http://localhost:8080/search?address=%EA%B0%95%EB%82%A8&page=0&size=10&sort=basicParkingFee,asc  
search with name: http://localhost:8080/search?name=%EC%B4%88%EC%95%88  
