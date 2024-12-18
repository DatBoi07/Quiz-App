1. Clone the Repository
Clone the project repository to your local machine.

3. Build the Application
Use Maven to build the application.
mvn clean install

3. Run the Application
Run the application using Maven or your preferred IDE.

Using Maven:
mvn spring-boot:run

Using IntelliJ IDEA:
Open the project in IntelliJ IDEA.
Navigate to the QuizAppApplication class.
Right-click and select Run 'QuizAppApplication'.

4. Access H2 Database Console
The H2 database console can be accessed at:
http://localhost:8080/h2-console

H2 Database Connection Details:

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password
Ensure the database connection matches the application.properties file.


Endpoints
The following API endpoints are available:

1. Start a New Quiz Session

URL: /api/quiz/start

Method: POST

Response:

{
  "sessionId": "<UUID>"
}

2. Get a Random Question

URL: /api/quiz/question

Method: GET

Query Parameters:

sessionId: The ID of the quiz session

Response:

{
  "id": 1,
  "text": "What is the capital of France?",
  "options": ["Paris", "London", "Berlin", "Madrid"],
  "correctOptionIndex": 0
}

3. Submit an Answer

URL: /api/quiz/submit

Method: POST

Request Body:

{
  "sessionId": "<UUID>",
  "questionId": 1,
  "selectedOptionIndex": 0
}

Response:

{
  "correct": true
}

4. Get Quiz Results

URL: /api/quiz/results

Method: GET

Query Parameters:

sessionId: The ID of the quiz session

Response:

{
  "totalQuestions": 3,
  "correctAnswers": 2,
  "incorrectAnswers": 1
}

Testing the API

Use a tool like Postman or cURL to test the API endpoints.

Example cURL Commands:

Start a Quiz Session:

curl -X POST http://localhost:8080/api/quiz/start

Get a Question:

curl -X GET "http://localhost:8080/api/quiz/question?sessionId=<UUID>"

Submit an Answer:

curl -X POST -H "Content-Type: application/json" \
-d '{"sessionId": "<UUID>", "questionId": 1, "selectedOptionIndex": 0}' \
http://localhost:8080/api/quiz/submit

Get Quiz Results:
curl -X GET "http://localhost:8080/api/quiz/results?sessionId=<UUID>"
