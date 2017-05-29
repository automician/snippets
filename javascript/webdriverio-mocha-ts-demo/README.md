# Steps
* [install nodejs](https://nodejs.org/en/)
* in the command line:
  
  * make directory for project
  * change directory to project's directory
  
  * `npm init`
     * answer for questions
     * `package.json` was created
  * make directory `test` 
  
  * ```npm install --save webdriverio 
       npm install --save mocha
       npm install --save typescript
       npm install --save ts-node```
  * explanations       
          * key `--save` is used to automatically save these dependencies in our package.json file
          * [`ts-node`](https://stackoverflow.com/questions/40910864/cannot-find-module-ts-node-register)
  
  * directory `node_modules` is created in project's folder
  * ```npm install --save @types/node
       npm install --save @types/mocha
       npm install --save @types/webdriverio
       npm install --save @types/typescript```
  * strings `@types/...` were appeared in `package.json`, in `dependencies` section
  * directory `@types` was created in the directory `node_modules`
    * [explanations](https://www.typescriptlang.org/docs/handbook/tsconfig-json.html#types-typeroots-and-types)  
  * change `package.json`
    * ```"scripts": {
          "pretest": "npm run tsc",
          "test": "./node_modules/.bin/mocha --reporter spec",
          "tsc": "tsc"
         },``` 
    * [explanations](https://docs.npmjs.com/scripts) 
  
  * create file `tsconfig.json`
    * option 1 - in the command line, run `tsc --init`
    * option 2 - do this through the context menu of project (in the WebStorm)
    * change `tsconfig.json` to
      * ```{
             "compilerOptions": {
               "module": "commonjs",
               "target": "es6",
               "strictNullChecks": true,
               "experimentalDecorators": true,
               "sourceMap": false,
               "declaration": false,
               "removeComments": false,
               "noImplicitAny": false,
               "types": ["mocha", "node", "webdriverio"]
             },
             "exclude": [
               "node_modules"
             ]
           }```
      * [explanations](https://www.typescriptlang.org/docs/handbook/tsconfig-json.html)   
  
  * in the `test` directory, add `mocha.opts` file
    * change `mocha.opts` : `--recursive`
      * [explanations](http://bpinto.github.io/posts/running-mocha-tests-on-subdirectories/)
 

  * add and fill `.gitignore`
  
  * add some test in the `test` directory (`test\google_spec.ts`)
  * answer `OK` for the question `Compile TypeScript to JavaScript?`
 
# Before test running
  * [download selenium server](http://www.seleniumhq.org/download/), look at `Selenium Standalone Server` chapter
  * run selenium server: `java -jar selenium-server-standalone-XXXX.jar`

# How to run tests?
  * in the command line: `npm test` 
  * in the WebStorm: `run` in context menu of ts-file
  
# After the project's cloning  
  * in the command line: `npm install` 
  * in the WebStorm: `run npm install` in context menu of project

# Steps in WebStorm
  * [WebStorm 7 - Integration of Mocha test framework](https://youtu.be/4mKiGkokyx8)
  * [Testing JavaScript with Mocha](https://www.jetbrains.com/help/webstorm/2017.1/testing-javascript-with-mocha.html)
  * [Run/Debug Configuration: Mocha](https://www.jetbrains.com/help/webstorm/2017.1/run-debug-configuration-mocha.html) 
