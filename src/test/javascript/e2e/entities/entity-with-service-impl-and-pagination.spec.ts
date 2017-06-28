import { browser, element, by, $ } from 'protractor';

describe('EntityWithServiceImplAndPagination e2e test', () => {

    const username = element(by.id('username'));
    const password = element(by.id('password'));
    const entityMenu = element(by.id('entity-menu'));
    const accountMenu = element(by.id('account-menu'));
    const login = element(by.id('login'));
    const logout = element(by.id('logout'));

    beforeAll(() => {
        browser.get('/');

        accountMenu.click();
        login.click();

        username.sendKeys('admin');
        password.sendKeys('admin');
        element(by.css('button[type=submit]')).click();
        browser.waitForAngular();
    });

    it('should load EntityWithServiceImplAndPaginations', () => {
        entityMenu.click();
        element.all(by.css('[routerLink="entity-with-service-impl-and-pagination"]')).first().click().then(() => {
            const expectVal = /jh4TestApp.entityWithServiceImplAndPagination.home.title/;
            element.all(by.css('h2 span')).first().getAttribute('jhiTranslate').then((value) => {
                expect(value).toMatch(expectVal);
            });
        });
    });

    it('should load create EntityWithServiceImplAndPagination dialog', () => {
        element(by.css('button.create-entity-with-service-impl-and-pagination')).click().then(() => {
            const expectVal = /jh4TestApp.entityWithServiceImplAndPagination.home.createOrEditLabel/;
            element.all(by.css('h4.modal-title')).first().getAttribute('jhiTranslate').then((value) => {
                expect(value).toMatch(expectVal);
            });

            element(by.css('button.close')).click();
        });
    });

    afterAll(() => {
        accountMenu.click();
        logout.click();
    });
});
