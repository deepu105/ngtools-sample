import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceClassAndDTOService,
    EntityWithServiceClassAndDTOPopupService,
    EntityWithServiceClassAndDTOComponent,
    EntityWithServiceClassAndDTODetailComponent,
    EntityWithServiceClassAndDTODialogComponent,
    EntityWithServiceClassAndDTOPopupComponent,
    EntityWithServiceClassAndDTODeletePopupComponent,
    EntityWithServiceClassAndDTODeleteDialogComponent,
    entityWithServiceClassAndDTORoute,
    entityWithServiceClassAndDTOPopupRoute,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceClassAndDTORoute,
    ...entityWithServiceClassAndDTOPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceClassAndDTOComponent,
        EntityWithServiceClassAndDTODetailComponent,
        EntityWithServiceClassAndDTODialogComponent,
        EntityWithServiceClassAndDTODeleteDialogComponent,
        EntityWithServiceClassAndDTOPopupComponent,
        EntityWithServiceClassAndDTODeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceClassAndDTOComponent,
        EntityWithServiceClassAndDTODialogComponent,
        EntityWithServiceClassAndDTOPopupComponent,
        EntityWithServiceClassAndDTODeleteDialogComponent,
        EntityWithServiceClassAndDTODeletePopupComponent,
    ],
    providers: [
        EntityWithServiceClassAndDTOService,
        EntityWithServiceClassAndDTOPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceClassAndDTOModule {}
