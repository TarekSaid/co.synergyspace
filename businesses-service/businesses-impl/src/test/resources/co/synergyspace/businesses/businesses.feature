@businesses
Feature: Business Operations
  As a business
  To form partnerships
  I want to be able to register and find businesses

  Background:
    Given that the following businesses exist
      | XPTO |
      | ABCD |

  Scenario: Find business by name
    When I search for the business "XPTO"
    Then I should see
    """
    \{"id":1,"name":"XPTO"\}
    """

  Scenario: Find all businesses
    When I list all businesses
    Then I should see
      """
      (\[\{"id":2,"name":"ABCD"\},\{"id":1,"name":"XPTO"\}\]|\[\{"id":1,"name":"XPTO"\},\{"id":2,"name":"ABCD"\}\])
      """

  Scenario: Add a Business
    Given that I am the business "Hello"
    When I register my business
    And I search for the business "Hello"
    Then I should see
      """
      \{"id":3,"name":"Hello"\}
      """