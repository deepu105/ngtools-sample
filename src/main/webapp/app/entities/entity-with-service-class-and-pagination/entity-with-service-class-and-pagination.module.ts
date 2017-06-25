import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceClassAndPaginationService,
    EntityWithServiceClassAndPaginationPopupService,
    EntityWithServiceClassAndPaginationComponent,
    EntityWithServiceClassAndPaginationDetailComponent,
    EntityWithServiceClassAndPaginationDialogComponent,
    EntityWithServiceClassAndPaginationPopupComponent,
    EntityWithServiceClassAndPaginationDeletePopupComponent,
    EntityWithServiceClassAndPaginationDeleteDialogComponent,
    entityWithServiceClassAndPaginationRoute,
    entityWithServiceClassAndPaginationPopupRoute,
    EntityWithServiceClassAndPaginationResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceClassAndPaginationRoute,
    ...entityWithServiceClassAndPaginationPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceClassAndPaginationComponent,
        EntityWithServiceClassAndPaginationDetailComponent,
        EntityWithServiceClassAndPaginationDialogComponent,
        EntityWithServiceClassAndPaginationDeleteDialogComponent,
        EntityWithServiceClassAndPaginationPopupComponent,
        EntityWithServiceClassAndPaginationDeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceClassAndPaginationComponent,
        EntityWithServiceClassAndPaginationDialogComponent,
        EntityWithServiceClassAndPaginationPopupComponent,
        EntityWithServiceClassAndPaginationDeleteDialogComponent,
        EntityWithServiceClassAndPaginationDeletePopupComponent,
    ],
    providers: [
        EntityWithServiceClassAndPaginationService,
        EntityWithServiceClassAndPaginationPopupService,
        EntityWithServiceClassAndPaginationResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceClassAndPaginationModule {}
