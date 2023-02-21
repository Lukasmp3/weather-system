# Weather System

Test project to get familiarize with Spring Boot.

## Usage:

Get history:
```
curl http://localhost:8080/weather/v1/history?city=Prague&dateTime=2007-12-03T10:15
```


Get forecast:
```
curl http://localhost:8080/weather/v1/forecast?city=Prague&dateTime=2030-12-03T10:15
```

Note that history service works can be run only on historical date time. \
On the contrary forecast service can be run only on future date time.

### Docker
```
mvn clean package
docker build -t weather-system-image .
docker run -p 8080:8080 weather-system-image
```