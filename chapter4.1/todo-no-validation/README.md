<p>RESTful API ?
<p>Use the most common HTTP methods: POST, PUT, PATCH, GET, and DELETE.
<p>@Valid 
<p>@RequestBody
<p>ResponseEntity
<p>ServletUriComponentsBuilder
<p>@PathVariable
<p>@ResponseStatus
<p>HttpStatus
<p>Content-Type : JSON/XML
<p>@ControllerAdvice 
<p> GET
curl -i http://localhost:8052/api/todos

<p> POST information ile 

curl -i -X POST -H "Content-Type: application/json" -d "{\"description\":\"Read the Pro Spring Boot 2nd Edition Book\"}" http://localhost:8052/api/todos

<p> POST informationsuz

curl -X POST -H "Content-Type: application/json" -d "{\"description\":\"Read the Pro Spring Boot 2nd Edition Book\"}" http://localhost:8052/api/todos

<p> POST validate ile

curl -i -X POST -H "Content-Type: application/json" http://localhost:8051/api/todos

<p> PUT

curl -i -X PUT -H "Content-Type: application/json" -d "{\"description\":\"Read the Book\",\"id\":\"d4f76070-24a9-4615-8750-79288cb1231d\"}" http://localhost:8051/api/todos

<p> PATCH

curl -i -X PATCH http://localhost:8051/api/todos/2d051b67-7716-4ee6-9c45-1de939fa579f

<p> DELETE
curl -i -X DELETE http://localhost:8051/api/todos/2d051b67-7716-4ee6-9c45-1de939fa579f
<p> DELETE validation ile
curl -i -X POST -H "Content-Type: application/json" -d "{\"description\":\"\"}" http://localhost:8051/api/todos

<p> pring elemek ucun

curl -s http://localhost:8051/api/todos | jq