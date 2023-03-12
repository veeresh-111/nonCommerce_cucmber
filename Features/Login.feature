Feature: Login 

@sanity
Scenario: Successful Login with Valid Credentials
	Given Userlaunch chrome browser
	When User open URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
  And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
  When User clcik on Log out link
  Then Page Title should be "Your store. Login"
  And close browser
	
	@regression
Scenario Outline: Login Data Driven
	Given Userlaunch chrome browser
	When User open URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "<email>" and Password as "<password>"
  And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
  When User clcik on Log out link
  Then Page Title should be "Your store. Login"
  And close browser
  
  Examples:
  | email | password |
  | admin@yourstore.com | admin |
  | admin@yourstore.com | admin123 |