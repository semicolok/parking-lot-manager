# parking-lot-manager  
## Setup  
Firstly check your network environment.  
This projects use external API.
You should check if you are in the network environment that allow it to access to the external API or not.
If you cannot, this project only use default data.  
#### How to check it   
```    
$ curl -vs http://openapi.seoul.go.kr:8088/your-auth-key/json/GetParkInfo/1/2/
```   
Secondly, to run this project, you need Java SDK(JDK) 1.8.  
Ensure that you have Java 8 installed and set to your JAVA_HOME.
#### How to check it  
```    
$ java -version  
java version "1.8.x_xxx"  
```   
#### Backend project  
[README.md](backend/README.md)  
#### Frontend project  
[README.md](frontend/README.md)  
#### Run and stop with docker-compose  
```    
$ ./gradlew clean dockerBuildImage
$ docker-compose up
$ docker-compose down
```  
#### URL  
http://localhost:3000  
