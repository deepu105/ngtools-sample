import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    DivisionService,
    DivisionPopupService,
    DivisionComponent,
    DivisionDetailComponent,
    DivisionDialogComponent,
    DivisionPopupComponent,
    DivisionDeletePopupComponent,
    DivisionDeleteDialogComponent,
    divisionRoute,
    divisionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...divisionRoute,
    ...divisionPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DivisionComponent,
        DivisionDetailComponent,
        DivisionDialogComponent,
        DivisionDeleteDialogComponent,
        DivisionPopupComponent,
        DivisionDeletePopupComponent,
    ],
    entryComponents: [
        DivisionComponent,
        DivisionDialogComponent,
        DivisionPopupComponent,
        DivisionDeleteDialogComponent,
        DivisionDeletePopupComponent,
    ],
    providers: [
        DivisionService,
        DivisionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestDivisionModule {}
