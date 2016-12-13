#Goal
To fix some approaches in widget usage
   
#"Done" list   
## core
  * profiles and properties - ensure running test using different environment properties linked with profiles
    * profile determines property file
    * one profile is default (if profile is not specified by user, default profile will be used) 
    * in project it can be possible to include several property files
    * each property file describes their own set of property values
    * property values can be clarified using system property with the same name 
    * it is convenient and flexible way to applying test settings
        * changing property file
        * using different profiles
        * using default profile
        * specifying some properties through system properties 
  * tests can be run in chrome and firefox
    * firefox with firefox driver usage (possible for v<=47.0.1) to avoid [some problems in geckodriver](https://github.com/mozilla/geckodriver/issues/233)
  * wait for Angular to finish async activity 
    * [ngWebDriver's](https://github.com/paul-hammant/ngWebDriver) realisation of that waiting was taken as an example
    * because of unstable results appeared in some cases without this waiting
  * Methods to access to elements with 'data-test' attribute
  * Unique data generator 
    * each generated value has a name
    * each value can be get by its name
  * One entry point to access core features
## widgets
  * object-oriented approach of tools realisation
  * widget describes some element (or group of linked elements)
  * widget provides methods
    * realizing all needed functions of that widget
    * returning widget(the same or new one - it depends on application logic)
  * widgets have one entry point
    * entry point is realised as a GIVEN(), WHEN(), THEN() methods
    * each call of widgets should begin from call one of these methods
    * thus, this approach can provide more readable code

#Profiles
* `dev` (by default)
* `test`

#Settings
* `app.url`
  * base url of tested application
* `data.user.email` and `data.user.password`
  * credentials of user - can be used in tests
* `test.timeout`
  * timeout in milliseconds for smart waits in situations
    * checks
    * access to elements 
    * that action run periodically during timeout until check is passed
    * this is native behaviour of Selenide allowing to test applications with dynamic load
* `test.browser`
  * firefox
  * chrome
   
#How to run from command line
* without details
  * `mvn clean test`
    * tests with default profile will be run
* using profile
  * `mvn clean test -P test`
    * tests with specified profile will be run
* using profile & system properties
  * `mvn clean test -P test -D test.browser=chrome`
    * tests with profile properties corrected by system properties will be run 
    * [example](https://drive.google.com/file/d/0B2UFaKOpHq_Mc1d1VVhDTmVQQzA/view?usp=sharing) 

#How to get Allure report
* command line to form report
  * `mvn clean test`
* opening
  * open in firefox - project\target\site\allure-maven-plugin.html
    * The report can be opened in more detail - down to the method steps (follow the arrows)
    * Each new sentence - starts with GIVEN / WHEN / THEN
    * The continuation of this sentence (methods of widgets) come after GIVEN / WHEN / THEN
  
       
    