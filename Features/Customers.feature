Feature: Customers

 Background: Below are common steps for every scenario
 	Given Userlaunch chrome browser
	When User open URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
  And Click on Login
  Then User can view Dashboard
  
 @sanity
 Scenario: Add new customer
  When User click on customers Menu
  And clcik on customers Menu Item
  And click on Add new button
  Then User can view Add new customer page
  When User enter customer info
  And click on save button
  Then User can view confirmation message "The new customer has been added successfully."
  And close browser
  
  @regression
 Scenario: Search Customer by EmailId
  When User click on customers Menu
  And clcik on customers Menu Item
  And Enter Customer Email
  When clcik on Search button
  Then User should found Email in the search table
  And close browser
  
  @regression
 Scenario: Search Customer by UserName
  When User click on customers Menu
  And clcik on customers Menu Item
  And Enter Customer FirstName
  And Enter Customer LastName
  When clcik on Search button
  Then User should found Name in the search table
  And close browser