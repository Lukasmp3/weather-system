# Weather System

Test project to get familiarize with Spring Boot.

## Usage:

Get history data:
```
curl http://localhost:8080/weather/v1/history?city=Prague&dateTime=2007-12-03T10:15
```


Get forecast:
```
curl http://localhost:8080/weather/v1/forecast?city=Prague&dateTime=2030-12-03T10:15
```