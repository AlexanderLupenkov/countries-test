# COUNTRIES

## 1. Source
 ##### Task is to create a simple Spring Boot service, that is able to calculate any possible land 
 ##### route from one country to another. The objective is to take a list of country data in JSON format 
 ##### and calculate the route by utilizing individual countries border information.

Specifications:  
* Spring Boot 
* Maven 
* Data link: `https://raw.githubusercontent.com/mledoze/countries/master/countries.json` 
* The application exposes REST endpoint `/routing/{origin}/{destination}` that returns a list of border crossings to get from origin to destination 
* Single route is returned if the journey is possible 
* Algorithm needs to be efficient 
* If there is no land crossing, the endpoint returns `HTTP 400`
* Countries are identified by `cca3` field in country data 
* HTTP request sample (land route from Czech Republic to Italy): 
  `GET /routing/CZE/ITA HTTP/1.0` : 
```json:
{
 "route": ["CZE", "AUT", "ITA"] 
}
```



## 2. How to run
### PORT IS 8080 BY DEFAULT BUT YOU CAN CHANGE IT MANUALLY IN `APPLICATION.PROPERTIES` / DOCKER-SCRIPT

There are 2 main ways to run the application:

1) Download the source project, open it by CLI and run `mvn spring-boot:run`

2) Run it by Docker:
Go to the root folder of project and run the following by CLI:
    - `docker build -t countries .`
    - `docker run -d -p 8080:8080 --rm countries:latest`
    
    where `countries` - is a name of image for example
    
After it you can test it by sending, for example, `GET`-request to `http://localhost:8080/api/v1/routes/PER/USA`, where `8080`- is a default port number and `PER` & `USA` are countries names
