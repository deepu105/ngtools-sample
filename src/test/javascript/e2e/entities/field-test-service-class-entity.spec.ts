import { browser, element, by, $ } from 'protractor';

describe('FieldTestServiceClassEntity e2e test', () => {

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

    it('should load FieldTestServiceClassEntities', () => {
        entityMenu.click();
        element.all(by.css('[routerLink="field-test-service-class-entity"]')).first().click().then(() => {
            const expectVal = /jh4TestApp.fieldTestServiceClassEntity.home.title/;
            element.all(by.css('h2 span')).first().getAttribute('jhiTranslate').then((value) => {
                expect(value).toMatch(expectVal);
            });
        });
    });

    it('should load create FieldTestServiceClassEntity dialog', () => {
        element(by.css('button.create-field-test-service-class-entity')).click().then(() => {
            const expectVal = /jh4TestApp.fieldTestServiceClassEntity.home.createOrEditLabel/;
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
