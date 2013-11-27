spring-boot-gradle-starter
==========================

#What is it?

Spring boot (http://projects.spring.io/spring-boot) is an interesting projetc, that aims at simplifying Spring project setup. At this point, though (version 0.5.0.M5) the documentation is a bit lacking, so it's not that easy to use. 

So if you have no experience with XML-less Spring setup, this simple project is an example, to checkout out.  It is based on official https://spring.io/guides/gs/spring-boot but extended and configured to include a set of tool, that you are likely to use (Groovy, Spock, Spring Data, etc.).

# How to run it?

If you have gradle installed and under linux/mac:

    gradle runJar

If gradle is not installed, but still under linux/mac

    gradlew runJar

And for windows without gradle

    gradlew.bat runJar

After the server is running, go to

```
http://localhost:8080/index.html
user: test
password: test 
```

The backend is done with 
- Spring Boot 0.5.0.M5
- Spring 4.0.0.M3
- Hibernate 4.2.1 
- Spring MVC, Spring Data JPA, Spring security and so on.

There is embedded tomcat and embedded, in-memory hsql inside.

There is a standard set of libs, like guava, joda-time and so on.

Backend is done with Java 7 and Groovy (you can safely mix both as long as you keep your sources in src/main/groovy folder), without any xml. Tests are written in groovy using Spock framework and one with Spring MVC (had to test if my view resolver is working, and that's only possible with spring Dispatcher/Front Controller).

Frontend is using old version of AngularJS, but it's just so you can fire it up and see it works. I used the
templating from scotch.io <http://scotch.io> but changed it out a bit. 

A nice tutorial is here: http://scotch.io/tutorials/javascript/single-page-apps-with-angularjs-routing-and-templating


