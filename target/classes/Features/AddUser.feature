@users
Feature: Adding the users to list
Background:
Given User is on reqres URL

Scenario Outline: Add user
When User enters the "<name>" and "<job>"
And users hits the user API
Then Users are added to list

Examples:
|name|job|
|Vengat|Manager|
|Praveen|Sr.Engnr|

@update
Scenario: Update the user
When User enters name & job
|Arun|Consultant|
|Prasanth|Engineer|
And user hits the API
Then User data is updated