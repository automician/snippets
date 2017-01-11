#Goal
Our goal was to illustrate some tools usage to determine test settings
* Gradle project
* [one config.groovy file for all profiles](https://github.com/automician/snippets/tree/master/java/properties/profiles-and-groovy-properties-gradle-demo/src/main/resources)
* [Helpers#getProperties() method for reading properties file and their correction according to system properties which were set in command line](https://github.com/automician/snippets/blob/master/java/properties/profiles-and-groovy-properties-gradle-demo/src/main/java/com/automician/javaproperties/core/Helpers.java)
 
 

#Examples
* Run in command line ```gradlew test -Pprofile=prod```
    
    information about properties in console 
    ```
    [Properties reading] ---------------------------------------------------------
    app.url = http://todomvc.com/examples/angularjs/
    test.browser = firefox
    [Properties reading] ---------------------------------------------------------
    ```
    
    this information corresponds to the config.groovy file
    ```
    environments {
    
        dev {
            app.url = 'http://todomvc.com/examples/angularjs/'
        }
    
        test {
            app.url = 'https://todomvc4tasj.herokuapp.com/'
        }
    
        prod {
            app.url = 'http://todomvc.com/examples/angularjs/'
            test.browser = 'firefox'
        }
    
    }
    
    test {
        browser = 'chrome'
    }
    ```

* Run in command line ```gradlew test -Pprofile=prod -Dtest.browser=chrome -Dapp.url=https://todomvc4tasj.herokuapp.com/```

    information about used properties in console 
    ```
     [Properties reading] ------------------------------------------
     app.url = http://todomvc.com/examples/angularjs/
     app.url = https://todomvc4tasj.herokuapp.com/ !!! corrected
     test.browser = firefox
     test.browser = chrome !!! corrected
     [Properties reading] ------------------------------------------
    ```
  
    properties were corrected according to the system properties filled through the command line
   

    
   