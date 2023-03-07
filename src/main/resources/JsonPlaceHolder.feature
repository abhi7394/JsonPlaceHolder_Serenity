Feature: Resource /post should be renamed to /posts
	Scenario: Posts endpoint accepts HTTP GET and provide all the available posts with status code as 200
		When User sends GET request to the endpoint "posts"
		Then It returns status code "200"
		And All posts count is not more than 100
	
	Scenario: Post end point returns 404 status when GET method is used
		When User sends GET request to the endpoint "post"
		Then It returns status code "404"
		
	Scenario Outline: The endpoint /posts with specific ID returns the post with same ID when GET method is used
		When User sends GET request to the "posts" with id as "<ID>"
		Then It returns status code "<StatusCode>"
		And Verify it has "<ID>", "<Title>" and "<Body>"	
		Examples:
		|	ID	|	StatusCode	|	Title	|	Body	|
		|	1	|	200			|	sunt aut facere repellat provident occaecati excepturi optio reprehenderit |	quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto|
		
	Scenario Outline: The endpoint /posts with ID greater than 100 will give 404 error
		When User sends GET request to the "posts" with id as "<ID>"
		Then It returns status code "<StatusCode>"		
		Examples:
		|	ID	|	StatusCode	|
		|	101	|	404			|
		
	Scenario: The endpoint /posts returns 500 internal server error status when HTTP POST method is used with the payload size greater than 10MB
		When User sends POST request to the endpoint "posts" with payload size greater than 10mb
		Then It returns status code "500"