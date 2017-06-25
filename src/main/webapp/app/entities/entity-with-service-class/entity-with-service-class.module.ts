import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceClassService,
    EntityWithServiceClassPopupService,
    EntityWithServiceClassComponent,
    EntityWithServiceClassDetailComponent,
    EntityWithServiceClassDialogComponent,
    EntityWithServiceClassPopupComponent,
    EntityWithServiceClassDeletePopupComponent,
    EntityWithServiceClassDeleteDialogComponent,
    entityWithServiceClassRoute,
    entityWithServiceClassPopupRoute,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceClassRoute,
    ...entityWithServiceClassPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceClassComponent,
        EntityWithServiceClassDetailComponent,
        EntityWithServiceClassDialogComponent,
        EntityWithServiceClassDeleteDialogComponent,
        EntityWithServiceClassPopupComponent,
        EntityWithServiceClassDeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceClassComponent,
        EntityWithServiceClassDialogComponent,
        EntityWithServiceClassPopupComponent,
        EntityWithServiceClassDeleteDialogComponent,
        EntityWithServiceClassDeletePopupComponent,
    ],
    providers: [
        EntityWithServiceClassService,
        EntityWithServiceClassPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceClassModule {}
