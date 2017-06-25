import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestPagerEntityService,
    FieldTestPagerEntityPopupService,
    FieldTestPagerEntityComponent,
    FieldTestPagerEntityDetailComponent,
    FieldTestPagerEntityDialogComponent,
    FieldTestPagerEntityPopupComponent,
    FieldTestPagerEntityDeletePopupComponent,
    FieldTestPagerEntityDeleteDialogComponent,
    fieldTestPagerEntityRoute,
    fieldTestPagerEntityPopupRoute,
    FieldTestPagerEntityResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...fieldTestPagerEntityRoute,
    ...fieldTestPagerEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestPagerEntityComponent,
        FieldTestPagerEntityDetailComponent,
        FieldTestPagerEntityDialogComponent,
        FieldTestPagerEntityDeleteDialogComponent,
        FieldTestPagerEntityPopupComponent,
        FieldTestPagerEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestPagerEntityComponent,
        FieldTestPagerEntityDialogComponent,
        FieldTestPagerEntityPopupComponent,
        FieldTestPagerEntityDeleteDialogComponent,
        FieldTestPagerEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestPagerEntityService,
        FieldTestPagerEntityPopupService,
        FieldTestPagerEntityResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestPagerEntityModule {}
