## Use the application

#### Requirements:
* JAVA version: 1.8 +
* MAVEN: 3.6.0

#### Build
* run `mvn clean install` at project root directory which contains pom.xml file.
* the jar file is built in target folder, and named as `weather-1.0-SNAPSHOT.jar`

#### Usage
* To Start the restful service, you can run `java -jar weather-1.0-SNAPSHOT.jar` in the target folder.
* Once the server is up and running, to read the API documentation you can use this url via the browser: `http://localhost:8080/doc`
* After the restful service is start running on port 8080, you can either use POSTMAN test tool to retrieve the information or use `curl` to retrieve the data.
* Example test use curl  ---  `curl -v http://localhost:8080/get_tomorrow_weather_detail?zipCode=85054`
* The Junit tests also have end to end API tests.
* To stop the server, `Ctrl + c`