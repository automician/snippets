# Goal
Our goal was to illustrate some tools usage to determine test settings
* [Maven profiles and their filtering](/java/properties/profiles-and-java-properties-demo/pom.xml)
* [java .properties files for each  profile](/java/properties/profiles-and-java-properties-demo/src/main/resources)
* [helper for reading properties file and their correction according to system properties which were set in command line](/java/properties/profiles-and-java-properties-demo/src/main/java/com/automician/javaproperties/core/Helpers.java)

# Examples
* Run in command line ```mvn clean test -P prod```
    
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

* Run in command line ```mvn clean test -P prod -D browser=chrome -D app.url=https://todomvc4tasj.herokuapp.com/```

    information about used properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    browser = firefox
    browser = chrome !!! corrected
    app.url = http://todomvc.com/examples/angularjs/
    app.url = https://todomvc4tasj.herokuapp.com/ !!! corrected
    [Properties reading] ---------------------------------------------------------
    ```
  
    properties were corrected according to the system properties filled through the command line
