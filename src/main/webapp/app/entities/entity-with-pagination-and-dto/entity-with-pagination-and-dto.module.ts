import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithPaginationAndDTOService,
    EntityWithPaginationAndDTOPopupService,
    EntityWithPaginationAndDTOComponent,
    EntityWithPaginationAndDTODetailComponent,
    EntityWithPaginationAndDTODialogComponent,
    EntityWithPaginationAndDTOPopupComponent,
    EntityWithPaginationAndDTODeletePopupComponent,
    EntityWithPaginationAndDTODeleteDialogComponent,
    entityWithPaginationAndDTORoute,
    entityWithPaginationAndDTOPopupRoute,
    EntityWithPaginationAndDTOResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entityWithPaginationAndDTORoute,
    ...entityWithPaginationAndDTOPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithPaginationAndDTOComponent,
        EntityWithPaginationAndDTODetailComponent,
        EntityWithPaginationAndDTODialogComponent,
        EntityWithPaginationAndDTODeleteDialogComponent,
        EntityWithPaginationAndDTOPopupComponent,
        EntityWithPaginationAndDTODeletePopupComponent,
    ],
    entryComponents: [
        EntityWithPaginationAndDTOComponent,
        EntityWithPaginationAndDTODialogComponent,
        EntityWithPaginationAndDTOPopupComponent,
        EntityWithPaginationAndDTODeleteDialogComponent,
        EntityWithPaginationAndDTODeletePopupComponent,
    ],
    providers: [
        EntityWithPaginationAndDTOService,
        EntityWithPaginationAndDTOPopupService,
        EntityWithPaginationAndDTOResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithPaginationAndDTOModule {}
