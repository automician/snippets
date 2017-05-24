"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const webdriverio = require("webdriverio");
describe('Google.com', function () {
    this.timeout(0);
    const options = {
        desiredCapabilities: { browserName: 'chrome' },
        waitforTimeout: 5000
    };
    const browser = webdriverio.remote(options);
    before('setup browser', function () {
        return __awaiter(this, void 0, void 0, function* () {
            yield browser.init();
        });
    });
    after('tear down browser', function () {
        return __awaiter(this, void 0, void 0, function* () {
            yield browser.end();
        });
    });
    it('searches text', function () {
        return __awaiter(this, void 0, void 0, function* () {
            yield browser.url('https://google.com/ncr');
            yield browser.element('[name=q]').setValue('webdriverio').keys('Enter');
            yield browser.waitUntil(() => __awaiter(this, void 0, void 0, function* () {
                return (yield browser.elements('.srg .g'))
                    .value.length == 10;
            }));
            yield browser.waitUntil(() => __awaiter(this, void 0, void 0, function* () {
                return (yield browser.element('.srg .g:nth-of-type(1)').getText())
                    .includes('WebdriverIO - WebDriver bindings for Node.js');
            }));
        });
    });
});
