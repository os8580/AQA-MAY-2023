Feature: First google tests

  Scenario: my first google test
    Given I load google page
    When i search for "Ryan Reynolds"
    Then i see word "Ryan Reynolds" in search hyperlinks

  Scenario Outline: my first google test
    Given sample step
    When sample when <value>
    Then sample then <value>
    Then I use multiple params <value> and <int_value>
    Given sample enum step SEARCH_INPUT
    Given sample list step:
      | "a" |
      | "b" |
      | "c" |
      | "d" |
      | "e" |
    Given sample map step:
      | "username" | "admin"          |
      | "password" | "admin"          |
      | "email"    | "admin@prog.org" |
      | "phone"    | "+456789123456"  |
    Examples:
      | value    | int_value |
      | "a test" | 1         |
      | "b test" | 2         |
      | "c test" | 3         |