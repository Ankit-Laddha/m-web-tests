<h2 align="center">M Web UI / API Automation Tests</h2>

----------------------

## Table of Contents

- [Framework](#framework)
- [Scenarios](#scenarios)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Usage](#usage)
- [Test Results](#test-results)

## Framework

```text
   Website under test: https://admin-advertisement.herokuapp.com/advertisements
   Tool: Selenium WebDriver
   Language: Java
   Reporting: Allure (Basic)
   Test Runner: TestNG
   Target Machine: Local
   Assertion: AssertJ library
   In-scope browsers: Chrome
```

----------------------

## Scenarios

* Here I'm considering two happy flows related to Advertisement

````text
1. Create new Advertisement
- Open homepage
- Click on '+'
- Add advertisement details and Save
- Check on homepage you see newly created advertisement

2. Edit Advertisement
- Open homepage
- Pick any existing advertisement (in case of automation, we are creating new one using APIs)
- Edit and save the details
- Check on homepage you see the edited advertisement with new details
````



----------------------

## Getting Started

Follow following instructions to get started with the project.

### Prerequisites

* Your jdk 11 is set up

* Make sure following version of browser is installed on your system for `local` run.
    - [Chrome v80+](https://www.google.com/chrome/)
    - There is no need to configure drivers, framework will detect your browser version and download a compatible driver
      to run the tests automatically

### Usage

* Tests are placed in package [here](src/test/java/tests)

* We will use maven commands to trigger our tests.

   ```bash
   mvn clean test 
   ```

--------------------------  

## Test Results

* Install Allure using command line

  ```bash
      brew install allure
  ```

* For Html report of run, open allure report using below command.

  ```bash
      allure serve target/allure-results  
  ```

* Sample HTML report of the local run is attached [here](web-tests-html-report.png)  
    
