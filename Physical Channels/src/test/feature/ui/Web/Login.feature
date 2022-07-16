Feature: Prepaid Web Login Feature

  @webtest
  Scenario: Successful Login to Prepaid Web Application
    Given user launch web application
    And user enters username "<username>" and password "<password>"
    When user clicks on Login
    #Then user is able to see home page
    #And user want to logout from application
    #And user navigate to Login screen
