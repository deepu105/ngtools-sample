import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceImplPaginationAndDTOService,
    EntityWithServiceImplPaginationAndDTOPopupService,
    EntityWithServiceImplPaginationAndDTOComponent,
    EntityWithServiceImplPaginationAndDTODetailComponent,
    EntityWithServiceImplPaginationAndDTODialogComponent,
    EntityWithServiceImplPaginationAndDTOPopupComponent,
    EntityWithServiceImplPaginationAndDTODeletePopupComponent,
    EntityWithServiceImplPaginationAndDTODeleteDialogComponent,
    entityWithServiceImplPaginationAndDTORoute,
    entityWithServiceImplPaginationAndDTOPopupRoute,
    EntityWithServiceImplPaginationAndDTOResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceImplPaginationAndDTORoute,
    ...entityWithServiceImplPaginationAndDTOPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceImplPaginationAndDTOComponent,
        EntityWithServiceImplPaginationAndDTODetailComponent,
        EntityWithServiceImplPaginationAndDTODialogComponent,
        EntityWithServiceImplPaginationAndDTODeleteDialogComponent,
        EntityWithServiceImplPaginationAndDTOPopupComponent,
        EntityWithServiceImplPaginationAndDTODeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceImplPaginationAndDTOComponent,
        EntityWithServiceImplPaginationAndDTODialogComponent,
        EntityWithServiceImplPaginationAndDTOPopupComponent,
        EntityWithServiceImplPaginationAndDTODeleteDialogComponent,
        EntityWithServiceImplPaginationAndDTODeletePopupComponent,
    ],
    providers: [
        EntityWithServiceImplPaginationAndDTOService,
        EntityWithServiceImplPaginationAndDTOPopupService,
        EntityWithServiceImplPaginationAndDTOResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceImplPaginationAndDTOModule {}
