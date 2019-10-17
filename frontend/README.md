# parking-lot-manager-frontend(Seoul)
#### Set environment variables  
```  
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
```  
#### Build project
This project has gradle wrapper. You can build it without installing gradle.  
```    
$ ../gradlew :frontend:clean :frontend:build && ls -al build
```  
#### Build docker image  
```  
$ ../gradlew :frontend:clean :frontend:dockerBuildImage
```  
#### generate Dockerfile  
```  
$ ../gradlew :frontend:dockerCreateDockerfile && cat build/docker/Dockerfile
```  
#### Run  
There are 2 ways you can run this project with. Run JAR file and use gradle "bootRun" task.  
##### jar run  
```
$ ../gradlew :frontend:clean :frontend:build  
$ java -jar build/libs/frontend-0.0.1-SNAPSHOT.jar
```  
##### gradle bootRun  
```  
$ ../gradlew :frontend:clean :frontend:bootRun
```  
#### URL
```    
$ open http://localhost:3000
```    
