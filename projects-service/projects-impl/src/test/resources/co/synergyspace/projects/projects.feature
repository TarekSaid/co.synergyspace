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

  Scenario: find a project
    Given that I have the following project:
      | name         | description                  | status |
      | SynergySpace | Manage Partners and Projects | ACTIVE |
    When I search for that project
    Then I should see
    """
    \{"id":\d+,"name":"SynergySpace","description":"Manage Partners and Projects","status":"ACTIVE","involved":null\}
    """

    Scenario: create a new Project
      Given that I have the business "Creator"
      When I add the project:
      | name | description | status |
      | Test | Test        | ACTIVE |
      Then I should see
      """
      \{"id":\d+,"name":"Creator","ownedProjects":\[\{"id":\d+,"name":"Test","description":"Test","status":"ACTIVE","involved":null\}\],"involvedIn":null\}
      """

  Scenario: Involving Businesses in a Project
    Given that I have the following project:
      | name | description | status |
      | One  | Test        | ACTIVE |
    When I involve the following businesses:
      | Leticia |
      | Joao    |
    Then I should see
    """
    \{"id":\d+,"name":"One","description":"Test","status":"ACTIVE","involved":\[\{"id":\d+,"name":"(Leticia|Joao)","ownedProjects":null,"involvedIn":null\},\{"id":\d+,"name":"(Leticia|Joao)","ownedProjects":null,"involvedIn":null\}\]\}
    """