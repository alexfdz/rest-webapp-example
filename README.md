rest-webapp
=============


Technology stack
--------------
* Spring framework
** spring-data
* JAXB
* Jersey
* Jackson

* Cucumber JVM
* Mockito
* Hamcrest
* JUnit
* Jetty


Build and run
--------------
    
Test(unit, integration and functional), build and install

    rest-webapp> mvn clean install
    
    
Run jetty container

	rest-webapp/web> mvn jetty:run
	
	
TODOs
--------------

* Disacouple the dependencies between the domain model and the schema model
* Integrate embedded mongodb instance
** embedded-mongodb-maven-plugin
* Maven properties/dependencies management/plugin management refactor