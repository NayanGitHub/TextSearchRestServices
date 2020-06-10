# TextSearchRestServices
The Search Text Rest Web Service Application is developed by Java 8 in Spring Boot(all Spring Features included). The security is provided by Spring Security with Basic Authentication.

The new Java 8 Lambda Expression features are used to remove duplication from List, Sorting the List

To run the application, open git bash in the desired location and execute the below steps:

1. $ git clone https://github.com/NayanGitHub/TextSearchRestServices.git

2. cd TextSearchRestServices/

3. $ mvn clean install

4. $ mvn spring-boot:run
   OR Import the Project to Eclipse and run the java class TextServicesApplication.java as Java Application

Run the Below test cases:

Test Case: 1

curl -X POST http://localhost:8080/counter-api/search -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d' {"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -H "Content-Type: application/json"

Results:

{"counts":[{"Duis":"11"},{"Sed":"16"},{"Donec":"8"},{"Augue":"7"},{"Pellentesque":"6"},{"123":"0"}]}


Test Case: 2

curl -X POST http://localhost:8080/counter-api/search -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d' {"searchText":["Hamller", "Juke", "Ipsum", "vel", "Duis", "908123"]}' -H "Content-Type: application/json"

Results:

{"counts":[{"Hamller":"0"},{"Juke":"0"},{"Ipsum":"11"},{"vel":"17"},{"Duis":"11"},{"908123":"0"}]}

Test Case: 3

curl http://localhost:8080/counter-api/top/20 -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H "Accept: text/csv"

Results:

eget|17
vel|17
Sed|16
sed|16
In|15
in|15
et|14
Ut|13
eu|13
ut|13
Nulla|12
ac|12
amet|12
id|12
metus|12
nulla|12
sit|12
Duis|11
at|11
ipsum|11

Test Case: 4

curl http://localhost:8080/counter-api/top/10 -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H "Accept: text/csv"

Results:

eget|17
vel|17
Sed|16
sed|16
In|15
in|15
et|14
Ut|13
eu|13
ut|13
