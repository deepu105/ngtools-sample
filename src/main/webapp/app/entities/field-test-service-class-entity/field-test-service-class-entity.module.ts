import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestServiceClassEntityService,
    FieldTestServiceClassEntityPopupService,
    FieldTestServiceClassEntityComponent,
    FieldTestServiceClassEntityDetailComponent,
    FieldTestServiceClassEntityDialogComponent,
    FieldTestServiceClassEntityPopupComponent,
    FieldTestServiceClassEntityDeletePopupComponent,
    FieldTestServiceClassEntityDeleteDialogComponent,
    fieldTestServiceClassEntityRoute,
    fieldTestServiceClassEntityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...fieldTestServiceClassEntityRoute,
    ...fieldTestServiceClassEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestServiceClassEntityComponent,
        FieldTestServiceClassEntityDetailComponent,
        FieldTestServiceClassEntityDialogComponent,
        FieldTestServiceClassEntityDeleteDialogComponent,
        FieldTestServiceClassEntityPopupComponent,
        FieldTestServiceClassEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestServiceClassEntityComponent,
        FieldTestServiceClassEntityDialogComponent,
        FieldTestServiceClassEntityPopupComponent,
        FieldTestServiceClassEntityDeleteDialogComponent,
        FieldTestServiceClassEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestServiceClassEntityService,
        FieldTestServiceClassEntityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestServiceClassEntityModule {}
