Feature: First google tests

#  Scenario: my first google test

  Scenario: my SQL test
    Given Get 1 random users from Web as "userGroup 1"
    Given Get 2 random users from Web as "userGroup 2"
    Given Get 3 random users from Web as "userGroup 3"
    Given I store "userGroup 2" in DB
    Given Get random user from group "userGroup 3" as "person 1"
    Given Get random user from DB as "person 2"
    Given Get random user from DB as "person 3"

    Given I load google page
    When i search for "person 1"
    Then i see name of "person 1" in search hyperlinks

    Given I load google page
    When i search for "person 2"
    Then i see name of "person 2" in search hyperlinks

    Given I load google page
    When i search for "person 3"
    Then i see name of "person 3" in search hyperlinks