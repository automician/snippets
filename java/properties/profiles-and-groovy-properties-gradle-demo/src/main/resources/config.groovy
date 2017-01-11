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