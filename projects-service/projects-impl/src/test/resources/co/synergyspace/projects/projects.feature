@projects
Feature: Projects Operations
  As a Business
  To have an overview of what I'm working on, with whom and how it's going
  I want to be able to create, edit, delete projects and add or remove involved businesses.

  Scenario: find all projects created by the business
    Given that I have the business "Test"
    And that I have the following projects:
      | name  | description     | status   |
      | Test  | Test Project    | ACTIVE   |
      | Hello | Another Project | INACTIVE |
    When I list my projects
    Then I should see
    """
    \[(?=.*\{"id":\d+,"name":"Test","description":"Test Project","status":"ACTIVE","involved":null\})(?=.*\{"id":\d+,"name":"Hello","description":"Another Project","status":"INACTIVE","involved":null\}).*\]
    """