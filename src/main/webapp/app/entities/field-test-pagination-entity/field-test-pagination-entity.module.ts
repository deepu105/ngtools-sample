import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestPaginationEntityService,
    FieldTestPaginationEntityPopupService,
    FieldTestPaginationEntityComponent,
    FieldTestPaginationEntityDetailComponent,
    FieldTestPaginationEntityDialogComponent,
    FieldTestPaginationEntityPopupComponent,
    FieldTestPaginationEntityDeletePopupComponent,
    FieldTestPaginationEntityDeleteDialogComponent,
    fieldTestPaginationEntityRoute,
    fieldTestPaginationEntityPopupRoute,
    FieldTestPaginationEntityResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...fieldTestPaginationEntityRoute,
    ...fieldTestPaginationEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestPaginationEntityComponent,
        FieldTestPaginationEntityDetailComponent,
        FieldTestPaginationEntityDialogComponent,
        FieldTestPaginationEntityDeleteDialogComponent,
        FieldTestPaginationEntityPopupComponent,
        FieldTestPaginationEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestPaginationEntityComponent,
        FieldTestPaginationEntityDialogComponent,
        FieldTestPaginationEntityPopupComponent,
        FieldTestPaginationEntityDeleteDialogComponent,
        FieldTestPaginationEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestPaginationEntityService,
        FieldTestPaginationEntityPopupService,
        FieldTestPaginationEntityResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestPaginationEntityModule {}
