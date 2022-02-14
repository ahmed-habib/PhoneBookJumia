* Running the Bake-end application on STS Using the embedded tomcat
	1- import the app into the STS
	2- Right click on the PhoneBookJumia application then choose "Run As" then choose "maven install" 
		to download the maven dependencies and run the Junit tests
	3- right click on the application then choose "Run As" then choose "Spring Boot App"

* Running the Bake-end application on docker
	1- CD to be inside the project
	2- Run the following command to generate the docker image
		docker build -f Dockerfile -t phonebookjumia .
	3- Run the following command to Run the image
		docker run -d -p 8080:8080 phonebookjumia
		
* You can open the swagger using the following URL
	http://localhost:8080/phonebook/swagger-ui.html
* You can access the Get customers API using the following URL
	http://localhost:8080/phonebook/api/customers