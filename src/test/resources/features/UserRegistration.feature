@Smoke @Regression
Feature: User Registration Functionality

Background:
	Given user opens registration page
	
Scenario: 1_User Registration
	When user clicks on register button
	And ensures necessary validations are displayed
	Then user selects their gender "Male"
  And user enters their first name "Ashwin" last name "Kumar" and email address "akash1@test.com"
  Then user enters "Test" in password and "Te" in confirm password fields
  And ensures the validation "The password should have at least 6 characters." is displayed for "Password" field
  And ensures the validation "The password and confirmation password do not match." is displayed for "Confirm Password" field
  Then user clears the passwprd fields
  Then user enters "Test@123" in password and "Test@123" in confirm password fields
  And user clicks on register button
  Then ensures the validation "Your registration completed" is displayed for "Registration Complete" field