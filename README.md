# refactoring-java
 Refactoring java Assignment by Abhijit Hibare
 The code creates an information slip about movie rentals.
 
### Approach
Changed plain java code to spring-boot rest api 
Expose an interactive REST API to get rental statements

### Assumptions
Change in Request:
1. Code upgrades to Springboot Rest API
2. Changed List to Set for Customer - List may contain same movie details twice but set will prevent it.
3. Creating MovieCategory Enum helps to avoid invalid movie categories 


### Future actions:
1. Add DB to maintain master data
2. Implement flyway to maintain DB scripts
3. Provide CRUD operation for maintaining Movies with its codes
4. Provide CRUD operation for maintaining Customers
5. If we want to maintain customer history then add rentalId in MovieRental class
6. Whiling move this assignment to production level then
    - Implement docker
    - Add github actions to push code from repo to cloud
    - Add terraform configuration to for IaC

### Usage

1. Get this repository in local
   ```shell
   git clone https://github.com/hibareabhijeet/refactoring-java.git
   ```
2. Enter into folder created from git clone
   ```shell
   cd refactoring-java
   ```
3. install `maven` dependencies
   ```shell
   mvn clean install
   ``` 
3. run application
   ```shell
   mvn spring-boot:run
   ```
### Prerequisite
1. Java 17 or higher version 
2. Maven 3.6.3 or higher

### API Details
<details>
<summary>

#### 1.`GET /api/rental/statement` _Gets rental statement for movies_
</summary>
<p>

###### Request
```postman
Import file to postman from src/main/resources/templates/refactoring-java.postman_collection.json

Or
```

```curl
curl --location --request GET 'localhost:8080/api/rental/statement' \
--header 'Content-Type: application/json' \
--data '{
	"name": "C. U. Stomer",
	"rentals": [
		{
			"movie": {
				"title": "You'\''ve Got Mail",
				"category": "REGULAR"
			},
			"days": 3
		},
		{
			"movie": {
				"title": "Matrix",
				"category": "REGULAR"
			},
			"days": 1
		}
	]
}'
```
</p>

<p>

###### Response
```
Rental Record for C. U. Stomer
	You've Got Mail	3.5
	Matrix	2.0
Amount owed is 5.5
You earned 2 frequent points
```
</p>
</details>


