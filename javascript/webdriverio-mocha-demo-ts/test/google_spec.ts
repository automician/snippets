import * as webdriverio from 'webdriverio'

describe('Google.com', function () {
    this.timeout(0);

    const options = {
        desiredCapabilities: {browserName: 'chrome'},
        waitforTimeout : 5000
    };

    const browser = webdriverio.remote(options);

    before('setup browser', async function () {
        await browser.init();
    });


    after('tear down browser', async function () {
        await browser.end();
    });

    it('searches text', async function () {
        await browser.url('https://google.com/ncr');
        await browser.element('[name=q]').setValue('webdriverio').keys('Enter');
        await browser.waitUntil(async () =>
        (await browser.elements('.srg .g'))
            .value.length == 10);
        await browser.waitUntil(async () =>
            (await browser.element('.srg .g:nth-of-type(1)').getText())
                .includes('WebdriverIO - WebDriver bindings for Node.js'))
    })
});