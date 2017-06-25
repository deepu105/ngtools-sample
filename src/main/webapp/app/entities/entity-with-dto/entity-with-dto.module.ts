import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithDTOService,
    EntityWithDTOPopupService,
    EntityWithDTOComponent,
    EntityWithDTODetailComponent,
    EntityWithDTODialogComponent,
    EntityWithDTOPopupComponent,
    EntityWithDTODeletePopupComponent,
    EntityWithDTODeleteDialogComponent,
    entityWithDTORoute,
    entityWithDTOPopupRoute,
} from './';

const ENTITY_STATES = [
    ...entityWithDTORoute,
    ...entityWithDTOPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithDTOComponent,
        EntityWithDTODetailComponent,
        EntityWithDTODialogComponent,
        EntityWithDTODeleteDialogComponent,
        EntityWithDTOPopupComponent,
        EntityWithDTODeletePopupComponent,
    ],
    entryComponents: [
        EntityWithDTOComponent,
        EntityWithDTODialogComponent,
        EntityWithDTOPopupComponent,
        EntityWithDTODeleteDialogComponent,
        EntityWithDTODeletePopupComponent,
    ],
    providers: [
        EntityWithDTOService,
        EntityWithDTOPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithDTOModule {}
