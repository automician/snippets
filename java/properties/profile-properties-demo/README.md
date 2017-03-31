# Goal
Our goal was to illustrate some tools usage to determine test settings
* [Maven profile properties](/java/properties/profile-properties-demo/pom.xml)
* [Maven surefire plugin to set system properties according to profile properties](/java/properties/profile-properties-demo/pom.xml)

# Examples
* Run in command line ```mvn clean test -P prod```

    information about properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    browser = firefox
    app.url = http://todomvc.com/examples/angularjs/
    [Properties reading] ---------------------------------------------------------
    ```
    
    this information corresponds to the profile properties
    ```
    <profile>
        <id>prod</id>
        <properties>
            <app.url>http://todomvc.com/examples/angularjs/</app.url>
            <browser>firefox</browser>
        </properties>
    </profile>
    ```

* Run in command line ```mvn clean test -P prod -D browser=chrome -D app.url=https://todomvc4tasj.herokuapp.com/```

    information about used properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    browser = chrome
    app.url = https://todomvc4tasj.herokuapp.com/ 
    [Properties reading] ---------------------------------------------------------
    ```
  
    properties were set according to the system properties filled through the command line
