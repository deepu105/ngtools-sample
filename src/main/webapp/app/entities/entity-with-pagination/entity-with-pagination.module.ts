import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithPaginationService,
    EntityWithPaginationPopupService,
    EntityWithPaginationComponent,
    EntityWithPaginationDetailComponent,
    EntityWithPaginationDialogComponent,
    EntityWithPaginationPopupComponent,
    EntityWithPaginationDeletePopupComponent,
    EntityWithPaginationDeleteDialogComponent,
    entityWithPaginationRoute,
    entityWithPaginationPopupRoute,
    EntityWithPaginationResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entityWithPaginationRoute,
    ...entityWithPaginationPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithPaginationComponent,
        EntityWithPaginationDetailComponent,
        EntityWithPaginationDialogComponent,
        EntityWithPaginationDeleteDialogComponent,
        EntityWithPaginationPopupComponent,
        EntityWithPaginationDeletePopupComponent,
    ],
    entryComponents: [
        EntityWithPaginationComponent,
        EntityWithPaginationDialogComponent,
        EntityWithPaginationPopupComponent,
        EntityWithPaginationDeleteDialogComponent,
        EntityWithPaginationDeletePopupComponent,
    ],
    providers: [
        EntityWithPaginationService,
        EntityWithPaginationPopupService,
        EntityWithPaginationResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithPaginationModule {}
