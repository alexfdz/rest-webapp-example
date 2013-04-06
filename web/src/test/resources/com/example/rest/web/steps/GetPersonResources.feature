Feature: A user queries for a person resource
  As a user
  I want to get a person resource information

 Scenario Outline: Person resource query  
   When I search for a valid person resource with id "<id>"
   Then the person resource family name is "<familiyName>"
  Examples:
    | id | familiyName |
    | 1 | Rooney |
    | 2 | Cameron |
    | 3 | Jackson |

 Scenario: Person resource query with incorrect id
   When I search for a valid person resource with id "19"
   Then the response fails with a not found error
   
 Scenario Outline: Person resource query with specific format  
   When I search for a valid person resource with id "1" and format "<format>"
   Then the response is successful
  Examples:
    | format           |
    | application/json |
    | application/xml  |
 
 Scenario: Person resource query with incorrect specific format  
   When I search for a valid person resource with id "1" and format "application/html"
   Then the response fails with an internal server error