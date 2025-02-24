/*
Write a MongoDB query to find the Total Number of Students in Each City
Print the output in the sorted order of the city name.

Collection: 'stucollection'

Reference Document:
----------------------
{
    "student_id": "S1001",
    "name": "Alice Johnson",
    "age": 18,
    "contact": {
      "email": "alice@example.com",
      "phone": "123-456-7890",
      "address": {
        "street": "123 Maple St",
        "city": "New York",
        "state": "NY",
        "zip": "10001"
      }
    },
    "enrolled_courses": ["Java", "Python"],
    "exams": [
      {
        "subject": "Java",
        "scores": [
          { "type": "quiz", "score": 80 },
          { "type": "midterm", "score": 75 },
          { "type": "final", "score": 90 }
        ],
        "passed": true
      },
      {
        "subject": "Python",
        "scores": [
          { "type": "quiz", "score": 70 },
          { "type": "midterm", "score": 65 },
          { "type": "final", "score": 85 }
        ],
        "passed": true
      }
    ],
    "skills": ["Java", "Spring Boot"],
    "guardian": {
      "name": "Robert Johnson",
      "relation": "Father",
      "contact": "robert.j@example.com"
    }
}

Sample Output:
--------------
[                                                                               
  {                                                                             
    total_students: 1,                                                          
    city: 'Atlanta'                                                             
  },                                                                            
  {                                                                             
    total_students: 1,                                                          
    city: 'Austin'                                                              
  }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
In db.<collection>.find():
	db -> Refers to the database.
	<collection> -> Your collection name
	find() -> Method to write the selection and projection part of the query.

*/
	
printjson(
	db.stucollection.aggregate([
	    {$group : {_id : "$contact.address.city",total_students : {$sum : 1} }},
	    {$sort : {_id : 1}},
	    {$project : {city : "$_id",total_students:1,_id:0}}
	    ])
)
