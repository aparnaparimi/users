users

Sping Boot application that calls an API, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London. 

Software used
users is written in Java 8 using Spring Boot and makes use of Lombok, Jackson and Lucene libraries.

Running the application locally
The source code of the application can be directly pulled into an IDE (e.g. IntelliJ, Eclipse) and run locally from inside there.

After the application is running, (assuming the application is running on localhost and port 8080) simply make a GET request to http://localhost:8080/london-users. This will return all users either listed as being from London or whose longatude and latitude place them within 50 miles of London.

Consuming the API
If you want to consume the API, please see the swagger.yaml file of this project. This includes details of the API, such as the endpoint of the API, a definition of the output of the API and a definition of the user object.

How the application works
The application returns the expected user list by first calling the downstream API using the path /city/London/users and then calling /users to get all users and filters to those within 50 miles of London based off their longitude and latitude. Afterwards these two lists are combined, removing duplicates, to return all users who are listed as either living in London, or whose current long, lat coordinates are within 50 miles of London.

To determine users based within 50 miles of London, the Haversine Method has been used. 
