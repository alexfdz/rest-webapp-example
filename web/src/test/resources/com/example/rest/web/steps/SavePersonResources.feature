Feature: A user saves a person resource
  As a user
  I want to save a person resource information

 Scenario Outline: Person resource save  
   When I submit a valid person resource with id "<id>" and name "<givenName>"
   Then the response is successful
  Examples:
    | id | givenName |
    | 6 | Messi |
    | 7 | Xavi |
    | 8 | Iniesta |
    
 Scenario: Invalid person resource save  
   When I submit an empty person resource
   Then the response fails with a bad request error