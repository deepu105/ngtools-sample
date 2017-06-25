import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestInfiniteScrollEntityService,
    FieldTestInfiniteScrollEntityPopupService,
    FieldTestInfiniteScrollEntityComponent,
    FieldTestInfiniteScrollEntityDetailComponent,
    FieldTestInfiniteScrollEntityDialogComponent,
    FieldTestInfiniteScrollEntityPopupComponent,
    FieldTestInfiniteScrollEntityDeletePopupComponent,
    FieldTestInfiniteScrollEntityDeleteDialogComponent,
    fieldTestInfiniteScrollEntityRoute,
    fieldTestInfiniteScrollEntityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...fieldTestInfiniteScrollEntityRoute,
    ...fieldTestInfiniteScrollEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestInfiniteScrollEntityComponent,
        FieldTestInfiniteScrollEntityDetailComponent,
        FieldTestInfiniteScrollEntityDialogComponent,
        FieldTestInfiniteScrollEntityDeleteDialogComponent,
        FieldTestInfiniteScrollEntityPopupComponent,
        FieldTestInfiniteScrollEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestInfiniteScrollEntityComponent,
        FieldTestInfiniteScrollEntityDialogComponent,
        FieldTestInfiniteScrollEntityPopupComponent,
        FieldTestInfiniteScrollEntityDeleteDialogComponent,
        FieldTestInfiniteScrollEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestInfiniteScrollEntityService,
        FieldTestInfiniteScrollEntityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestInfiniteScrollEntityModule {}
