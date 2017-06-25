import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestMapstructEntityService,
    FieldTestMapstructEntityPopupService,
    FieldTestMapstructEntityComponent,
    FieldTestMapstructEntityDetailComponent,
    FieldTestMapstructEntityDialogComponent,
    FieldTestMapstructEntityPopupComponent,
    FieldTestMapstructEntityDeletePopupComponent,
    FieldTestMapstructEntityDeleteDialogComponent,
    fieldTestMapstructEntityRoute,
    fieldTestMapstructEntityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...fieldTestMapstructEntityRoute,
    ...fieldTestMapstructEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestMapstructEntityComponent,
        FieldTestMapstructEntityDetailComponent,
        FieldTestMapstructEntityDialogComponent,
        FieldTestMapstructEntityDeleteDialogComponent,
        FieldTestMapstructEntityPopupComponent,
        FieldTestMapstructEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestMapstructEntityComponent,
        FieldTestMapstructEntityDialogComponent,
        FieldTestMapstructEntityPopupComponent,
        FieldTestMapstructEntityDeleteDialogComponent,
        FieldTestMapstructEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestMapstructEntityService,
        FieldTestMapstructEntityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestMapstructEntityModule {}
