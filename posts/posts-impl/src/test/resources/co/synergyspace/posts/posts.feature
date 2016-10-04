@posts
Feature: Posts Operations
  As a Business
  To promote myself and exchange information
  I want to be able to create and see posts

  Scenario: find all posts from a business
    Given that I have the Business "Test"
    And that I have the following posts
      | Hello |
      | Test  |
    When I list my posts
    Then I should see
      """
      [{"id":2,"date":null,"content":"Test"},{"id":1,"date":null,"content":"Hello"}]
      """

  Scenario: add a new Post
    Given that I have the Business "Hello"
    When I add the Post "test"
    Then I should see
      """
      {"id":3,"name":"Hello","posts":[{"id":4,"date":null,"content":"test"}]}
      """