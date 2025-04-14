@TagTest
Feature: Login Functionality

Scenario: 1_Login with valid credentials
	Given user opens login page
	Then user tries to login with email "testuser_ak@example.com" and password "TestPassword123"
	And clicks the login button
  Then the user should see their account page