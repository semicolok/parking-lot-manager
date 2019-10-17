# parking-lot-manager  
## Setup  
* To run this project, you need Java SDK(JDK) 1.8.  
Ensure that you have Java 8 installed and set to your JAVA_HOME.  
#### Backend project  
[README.md](backend/README.md)  
#### Frontend project  
[README.md](frontend/README.md)  
#### Run and stop with docker-compose  
```    
$  ./gradlew clean dockerBuildImage
$ docker-compose up
$ docker-compose down
```  
#### URL  
http://localhost:3000  


Important
---------
When **parking-lot-backend** boots up, it starts to prepare data to serve. It takes 2 mins.  
Until it is finished, **parking-lot-frontend** can not get any data from the backend.  

### Data preparation process  
**parking-lot-backend** has batch to initialize database. Get parking lot data from **providers** and save them to **Database**.  
Every 5 minutes, data in database are updated.  
#### Why we need it  
Providers doesn't provide search functions we need such as sorting, searching by address and response data count limit.  
And sometime they are too slow to use in real time application  
Their response time takes 2~3 seconds.  
These are reason why we need to copy them to our database.  
