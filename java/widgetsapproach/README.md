# Goal
To fix some approaches in widget usage

# "Done" list   
## the "core" part of test framework
  * profiles and properties 
    * ensure running tests using different environment 
    * java properties files are linked to profiles in pom.xml
    * one profile is default: profiles.dev
      * if profile is not specified by user, default profile will be used
    * there can be as many java properties files as needed
    * each property file describes their own set of values for the same list of properties
    * property values can be overridden using system property with the same name 
    * so tests can be configured by
        * changing property file
        * using different profiles
        * using default profile
        * specifying some properties through system properties 
  * tests can be run in chrome and firefox
    * for firefox the "older" firefox driver is used (for possible versions of the browser <=47.0.1) to avoid [some problems in geckodriver](https://github.com/mozilla/geckodriver/issues/233)
  * included: wait for Angular 
    * to finish async "loading" activity in case UI is not ready for corresponding actions and there is no other way to wait (e.g. via Selenide)
    * based on open source [ngWebDriver's](https://github.com/paul-hammant/ngWebDriver) implementation
    * for smart waiting of finishing async "loading" used ExpectedCondition based on wait for Angular
    * to use element's or collection's methods with these waits realized wrappers around SelenideElement element and ElementsCollection    
  * Unique data generator 
    * each generated value has a name
    * each value can be get by its name
    * only the "name" is used in tests, without directly specifying the "unique" prefix of postfix of data
    * and the "unique" part is hidden under the hood... 
    * so everything just works
  * One entry point to access core features

## The Test Model part
  * based on Widgets pattern (aka [PageObjects by Martin Fowler](http://martinfowler.com/bliki/PageObject.html))
  * implemented in object-oriented style for convenient handy usage in tests
  * widget describes some complex element, usually containing a group of other nested elements
  * widget can reflect the part of a page or a whole page
  * widget represents a behavior of the user on the "UI Part" that it represents
    * implemented in widget methods
    * returning widget(the same or new one - it depends on application logic)
      * so it can be conveniently used without much knowledge of its internal structure:
        `WHEN().topBar().startLogin().startSubscribe();`
  * in tests widgets have one entry point
    * entry point is implemented in GIVEN(), WHEN(), THEN() methods of BaseTest
    * so each widget call begins from call one of these methods
    * thus, this approach can provide more readable code in BDD style:
      [reported correspondingly](https://drive.google.com/file/d/0B2UFaKOpHq_MNEM4Y3NTRTAzdlU/view?usp=sharing)
      
# Profiles
* `dev` (by default)
* `test`

# Settings
* `data.user.email` and `data.user.password`
  * credentials of user - to be used in tests
* `test.timeout`
  * timeout in milliseconds 
    * for smart waits 
      * "smart" means - the "wait implementation" will wait only the minimum needed amount of time, not greater than specified timeout
    * during
      * asserts / checking test steps results
      * access to elements 
* `test.browser`
  * firefox
  * chrome
   
# How to run from command line
* without details
  * `mvn clean test`
    * tests with default profile will be run
* using profile
  * `mvn clean test -Ptest`
    * tests with specified profile will be run
* using profile & system properties
  * `mvn clean test -Ptest -Dtest.browser=chrome`
    * tests with profile properties overridden by system properties will be executed
    * [example](https://drive.google.com/file/d/0B2UFaKOpHq_Mc1d1VVhDTmVQQzA/view?usp=sharing) 

# How to get Allure report
* command line command to generate a report (based on test results of previously executed `mvn clean test`)
  * `mvn site`
* to open
  * open in firefox - project\target\site\allure-maven-plugin.html
  
