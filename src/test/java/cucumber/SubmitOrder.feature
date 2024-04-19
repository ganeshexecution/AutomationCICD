
@tag
Feature: Purchase products form E-Commerce website
  I want to use this template for my feature file

	Background:
	Given Landed on the ECommerce website

  @Regression
  Scenario Outline: To check the positive flow of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <product> to cart
    And Checkout with product <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the screen 

    Examples: 
      | name 									 | 	password 		| product  |
      | tonystark123@gmail.com | 	Tony@123 		| ADIDAS ORIGINAL |
    