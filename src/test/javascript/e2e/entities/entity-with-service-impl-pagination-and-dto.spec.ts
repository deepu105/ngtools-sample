import { browser, element, by, $ } from 'protractor';

describe('EntityWithServiceImplPaginationAndDTO e2e test', () => {

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

    it('should load EntityWithServiceImplPaginationAndDTOS', () => {
        entityMenu.click();
        element.all(by.css('[routerLink="entity-with-service-impl-pagination-and-dto"]')).first().click().then(() => {
            const expectVal = /jh4TestApp.entityWithServiceImplPaginationAndDTO.home.title/;
            element.all(by.css('h2 span')).first().getAttribute('jhiTranslate').then((value) => {
                expect(value).toMatch(expectVal);
            });
        });
    });

    it('should load create EntityWithServiceImplPaginationAndDTO dialog', function () {
        element(by.css('button.create-entity-with-service-impl-pagination-and-dto')).click().then(() => {
            const expectVal = /jh4TestApp.entityWithServiceImplPaginationAndDTO.home.createOrEditLabel/;
            element.all(by.css('h4.modal-title')).first().getAttribute('jhiTranslate').then((value) => {
                expect(value).toMatch(expectVal);
            });

            element(by.css('button.close')).click();
        });
    });

    afterAll(function () {
        accountMenu.click();
        logout.click();
    });
});
