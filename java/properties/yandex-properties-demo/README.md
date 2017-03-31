# Goal
Our goal was to illustrate some tools usage to determine test settings
* [Yandex QATools Properties](https://github.com/qatools/properties)
* [java .properties files for each properties set](/java/properties/yandex-properties-demo/src/main/resources)
* [helper for reading properties](/java/properties/yandex-properties-demo/src/main/java/com/automician/yandexproperties/core/Helpers.java)
  * Properties set reading process provided using factory method pattern for better scalability (this approach differs from QATools approach)  

# Examples
* Run in command line ```mvn clean test -D env=prod```
    
    in this version system property `env` is used to determine properties set
    
    information about properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    browser = firefox
    app.url = http://todomvc.com/examples/angularjs/
    [Properties reading] ---------------------------------------------------------
    ```
    
    this information corresponds to the prod.properties file
    ```
    app.url=http://todomvc.com/examples/angularjs/
    browser=firefox
    ```

* Run in command line ```mvn clean test -D env=prod -D browser=chrome -D app.url=https://todomvc4tasj.herokuapp.com/```

    information about used properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    app.url = https://todomvc4tasj.herokuapp.com/
    browser = chrome
    [Properties reading] ---------------------------------------------------------
    ```
  
    Yandex QATools Properties values can be overriden using system properties value.
