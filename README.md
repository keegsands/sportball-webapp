# Sportball Webapp

A simple web application for managing a sports league including scheduling and keeping track of standings.

## Getting Started

Check out the project and build it using Maven to generate the war file.

### Prerequisites

In order to build this project you will need to install Java JDK 8+ and Maven 3.5.0+.  If you want to deploy this you will need to install a Postgres database and also have a Tomcat 8+ server installed and ready to receive the webapp.

```
Java JDK 8
Maven 3.5+
Tomcat8+
Postgres 9.4+
```

### Installing

You will need to configure the database connection string and mail configuration inside Tomcat's context.xml file



## Running the tests

There are a few tests that can be run using `mvn clean test`

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Spring Security](https://spring.io/projects/spring-security) - Security framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Hibernate](http://hibernate.org) - Used for handling database access
* [Java](http://www.oracle.com/technetwork/java/index.html) - Used for handling user interface generation 


## Versioning

We use [SemVer](http://semver.org/) for versioning. 

## Authors

* **Keegan Sands** - *Initial work* - 


## License

This project is licensed under the MIT License <!-- - see the [LICENSE.md](LICENSE.md) file for details-->

## Acknowledgments

* Thanks to the TAP softball league for inspiring me to write this application
