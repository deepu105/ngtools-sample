import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestEntityService,
    FieldTestEntityPopupService,
    FieldTestEntityComponent,
    FieldTestEntityDetailComponent,
    FieldTestEntityDialogComponent,
    FieldTestEntityPopupComponent,
    FieldTestEntityDeletePopupComponent,
    FieldTestEntityDeleteDialogComponent,
    fieldTestEntityRoute,
    fieldTestEntityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...fieldTestEntityRoute,
    ...fieldTestEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestEntityComponent,
        FieldTestEntityDetailComponent,
        FieldTestEntityDialogComponent,
        FieldTestEntityDeleteDialogComponent,
        FieldTestEntityPopupComponent,
        FieldTestEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestEntityComponent,
        FieldTestEntityDialogComponent,
        FieldTestEntityPopupComponent,
        FieldTestEntityDeleteDialogComponent,
        FieldTestEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestEntityService,
        FieldTestEntityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestEntityModule {}
