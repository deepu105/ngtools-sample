import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    EntityWithServiceImplService,
    EntityWithServiceImplPopupService,
    EntityWithServiceImplComponent,
    EntityWithServiceImplDetailComponent,
    EntityWithServiceImplDialogComponent,
    EntityWithServiceImplPopupComponent,
    EntityWithServiceImplDeletePopupComponent,
    EntityWithServiceImplDeleteDialogComponent,
    entityWithServiceImplRoute,
    entityWithServiceImplPopupRoute,
} from './';

const ENTITY_STATES = [
    ...entityWithServiceImplRoute,
    ...entityWithServiceImplPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntityWithServiceImplComponent,
        EntityWithServiceImplDetailComponent,
        EntityWithServiceImplDialogComponent,
        EntityWithServiceImplDeleteDialogComponent,
        EntityWithServiceImplPopupComponent,
        EntityWithServiceImplDeletePopupComponent,
    ],
    entryComponents: [
        EntityWithServiceImplComponent,
        EntityWithServiceImplDialogComponent,
        EntityWithServiceImplPopupComponent,
        EntityWithServiceImplDeleteDialogComponent,
        EntityWithServiceImplDeletePopupComponent,
    ],
    providers: [
        EntityWithServiceImplService,
        EntityWithServiceImplPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityWithServiceImplModule {}
