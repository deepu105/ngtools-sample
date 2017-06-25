import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceImplAndPaginationService,
    EntityWithServiceImplAndPaginationPopupService,
    EntityWithServiceImplAndPaginationComponent,
    EntityWithServiceImplAndPaginationDetailComponent,
    EntityWithServiceImplAndPaginationDialogComponent,
    EntityWithServiceImplAndPaginationPopupComponent,
    EntityWithServiceImplAndPaginationDeletePopupComponent,
    EntityWithServiceImplAndPaginationDeleteDialogComponent,
    entityWithServiceImplAndPaginationRoute,
    entityWithServiceImplAndPaginationPopupRoute,
    EntityWithServiceImplAndPaginationResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceImplAndPaginationRoute,
    ...entityWithServiceImplAndPaginationPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceImplAndPaginationComponent,
        EntityWithServiceImplAndPaginationDetailComponent,
        EntityWithServiceImplAndPaginationDialogComponent,
        EntityWithServiceImplAndPaginationDeleteDialogComponent,
        EntityWithServiceImplAndPaginationPopupComponent,
        EntityWithServiceImplAndPaginationDeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceImplAndPaginationComponent,
        EntityWithServiceImplAndPaginationDialogComponent,
        EntityWithServiceImplAndPaginationPopupComponent,
        EntityWithServiceImplAndPaginationDeleteDialogComponent,
        EntityWithServiceImplAndPaginationDeletePopupComponent,
    ],
    providers: [
        EntityWithServiceImplAndPaginationService,
        EntityWithServiceImplAndPaginationPopupService,
        EntityWithServiceImplAndPaginationResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceImplAndPaginationModule {}
