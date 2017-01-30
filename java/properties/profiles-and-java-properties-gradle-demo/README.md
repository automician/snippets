#Goal
Our goal was to illustrate some tools usage to determine test settings
* Gradle project
* [java .properties files for each  profile](https://github.com/automician/snippets/tree/master/java/properties/profiles-and-java-properties-gradle-demo/src/main/resources)
* [PropertiesHelper methods for reading properties file and their correction according to system properties which were set in command line](https://github.com/automician/snippets/blob/master/java/properties/profiles-and-java-properties-gradle-demo/src/main/java/com/automician/javaproperties/core/PropertiesHelper.java)
* [Helpers#waitUntilJQueryLoaded() method to wait until JQuery is loaded](https://github.com/automician/snippets/blob/master/java/properties/profiles-and-java-properties-gradle-demo/src/main/java/com/automician/javaproperties/core/Helper.java)
* [conditions.Have#jsReturnedTrue condition to implement waiting until some JavaScript code returns true](https://github.com/automician/snippets/blob/master/java/properties/profiles-and-java-properties-gradle-demo/src/main/java/com/automician/javaproperties/core/conditions/Have.java)


#Examples
* Run in command line ```gradlew test -Pprofile=prod```
    
    information about properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    browser = firefox
    app.url = http://todomvc.com/examples/angularjs/
    [Properties reading] ---------------------------------------------------------
    ```
    
    this information corresponds to the .properties file
    ```
    app.url=http://todomvc.com/examples/angularjs/
    browser=firefox
    ```

* Run in command line ```gradlew test -Pprofile=prod -Dapp.url=https://todomvc4tasj.herokuapp.com/```

    information about used properties in console 
    ```
    [Properties reading] --------------------------------------------
    test.on = local
    test.local.browser = chrome
    app.url = http://todomvc.com/examples/angularjs/
    app.url = https://todomvc4tasj.herokuapp.com/ !!! corrected
    [Properties reading] --------------------------------------------
    ```
  
    properties were corrected according to the system properties filled through the command line
    
#Environment options
* `dev` 
* `prod`
* `test`(by default)

Environment option determines property file which will be used to run tests
#Settings
* `app.url`
    * base url of tested application
* `test.on`
    * local
         * `test.local.browser`
             * firefox
             * chrome             
    * grid
         * `test.grid.browser`
             * firefox
             * chrome
         * `test.grid.url` - node url               
    * sauceLabs
         * `test.sauceLabs.userName` and `test.sauceLabs.automateKey` (credentials of Sause Labs account)
         * `test.sauceLabs.capabilities.XXX` properties to [configure specific platform and browser](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/)
             * `test.sauceLabs.capabilities.capabilityName=capabilityValue`
    * browserStack
         * `test.browserStack.userName` and `test.browserStack.automateKey` - account credentials
         * `test.browserStack.capabilities.XXX` properties to [configure specific platform and browser](https://www.browserstack.com/automate/java#configure-capabilities)
             * `test.browserStack.capabilities.capabilityName=capabilityValue`
* Some details
    * chrome
         * __!!! to use it the additional actions are needed: download chromedriver and include path to chromedriver into environment variable PATH__
    * grid and firefox
         * to run node where firefox can be used it is important to use  `-Dwebdriver.firefox.marionette=false`
             * `java -Dwebdriver.firefox.marionette=false -jar selenium-server-standalone-3.0.1.jar -role node -hub http://.../grid/register -port …`     
   

    
   