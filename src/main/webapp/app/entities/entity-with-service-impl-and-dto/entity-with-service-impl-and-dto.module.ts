import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceImplAndDTOService,
    EntityWithServiceImplAndDTOPopupService,
    EntityWithServiceImplAndDTOComponent,
    EntityWithServiceImplAndDTODetailComponent,
    EntityWithServiceImplAndDTODialogComponent,
    EntityWithServiceImplAndDTOPopupComponent,
    EntityWithServiceImplAndDTODeletePopupComponent,
    EntityWithServiceImplAndDTODeleteDialogComponent,
    entityWithServiceImplAndDTORoute,
    entityWithServiceImplAndDTOPopupRoute,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceImplAndDTORoute,
    ...entityWithServiceImplAndDTOPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceImplAndDTOComponent,
        EntityWithServiceImplAndDTODetailComponent,
        EntityWithServiceImplAndDTODialogComponent,
        EntityWithServiceImplAndDTODeleteDialogComponent,
        EntityWithServiceImplAndDTOPopupComponent,
        EntityWithServiceImplAndDTODeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceImplAndDTOComponent,
        EntityWithServiceImplAndDTODialogComponent,
        EntityWithServiceImplAndDTOPopupComponent,
        EntityWithServiceImplAndDTODeleteDialogComponent,
        EntityWithServiceImplAndDTODeletePopupComponent,
    ],
    providers: [
        EntityWithServiceImplAndDTOService,
        EntityWithServiceImplAndDTOPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceImplAndDTOModule {}
