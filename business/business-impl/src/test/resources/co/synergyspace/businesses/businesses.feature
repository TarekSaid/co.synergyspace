@businesses
Feature: Business Operations
  As a business
  To form partnerships
  I want to find a business by name or list them all

  Background:
    Given that the following businesses exist
      | 1 | XPTO |
      | 2 | ABCD |

  Scenario: Find business by name
    When I search for the business "XPTO"
    Then I should see
    """
    {"id":1,"name":"XPTO"}
    """

  Scenario: Find all businesses
    When I list all businesses
    Then I should see
      """
      [{"id":1,"name":"XPTO"},{"id":2,"name":"ABCD"}]
      """

    Scenario: Add a Business
      Given that I am the business "Hello"
      When I register my business
      And I search for the business "Hello"
      Then I should see
      """
      {"id":3,"name":"Hello"}
      """