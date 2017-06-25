import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceClassPaginationAndDTOService,
    EntityWithServiceClassPaginationAndDTOPopupService,
    EntityWithServiceClassPaginationAndDTOComponent,
    EntityWithServiceClassPaginationAndDTODetailComponent,
    EntityWithServiceClassPaginationAndDTODialogComponent,
    EntityWithServiceClassPaginationAndDTOPopupComponent,
    EntityWithServiceClassPaginationAndDTODeletePopupComponent,
    EntityWithServiceClassPaginationAndDTODeleteDialogComponent,
    entityWithServiceClassPaginationAndDTORoute,
    entityWithServiceClassPaginationAndDTOPopupRoute,
    EntityWithServiceClassPaginationAndDTOResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceClassPaginationAndDTORoute,
    ...entityWithServiceClassPaginationAndDTOPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceClassPaginationAndDTOComponent,
        EntityWithServiceClassPaginationAndDTODetailComponent,
        EntityWithServiceClassPaginationAndDTODialogComponent,
        EntityWithServiceClassPaginationAndDTODeleteDialogComponent,
        EntityWithServiceClassPaginationAndDTOPopupComponent,
        EntityWithServiceClassPaginationAndDTODeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceClassPaginationAndDTOComponent,
        EntityWithServiceClassPaginationAndDTODialogComponent,
        EntityWithServiceClassPaginationAndDTOPopupComponent,
        EntityWithServiceClassPaginationAndDTODeleteDialogComponent,
        EntityWithServiceClassPaginationAndDTODeletePopupComponent,
    ],
    providers: [
        EntityWithServiceClassPaginationAndDTOService,
        EntityWithServiceClassPaginationAndDTOPopupService,
        EntityWithServiceClassPaginationAndDTOResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceClassPaginationAndDTOModule {}
