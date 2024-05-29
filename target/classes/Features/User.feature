Feature: Get all List of uers
Scenario: Get all user
Given user is on requested URL
When user hits the Users API
Then all the users is displayed
