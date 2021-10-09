Feature: Greeting API
  Send a greeting

  Scenario: Greeting with an empty name
    When greeted with an empty name
    Then greet back with "Hello, World"

  Scenario: Greeting with a name
    When greeted with a name "David"
    Then greet back with "Hello, David"

  Scenario: Save Greeting
    When saving the following greeting list
      | name | dateTime            | greeting   |
      | Tom  | 2021-01-01T12:00:00 | Hello, Tom |
    Then get back the greeting list
      | name | dateTime            | greeting   |
      | Tom  | 2021-01-01T12:00:00 | Hello, Tom |

  Scenario: Find Greeting when it is available
    Given the following greeting is available
      | name     | Dick                |
      | dateTime | 2021-10-01T13:00:00 |
      | greeting | Hello, Dick         |
    When finding the greeting for "Dick"
    Then get back the greeting list
      | name | dateTime           | greeting    |
      | Dick | 2021-10-01T13:00:00 | Hello, Dick |