import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jh4TestSharedModule } from '../../shared';
import {
    FieldTestServiceImplEntityService,
    FieldTestServiceImplEntityPopupService,
    FieldTestServiceImplEntityComponent,
    FieldTestServiceImplEntityDetailComponent,
    FieldTestServiceImplEntityDialogComponent,
    FieldTestServiceImplEntityPopupComponent,
    FieldTestServiceImplEntityDeletePopupComponent,
    FieldTestServiceImplEntityDeleteDialogComponent,
    fieldTestServiceImplEntityRoute,
    fieldTestServiceImplEntityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...fieldTestServiceImplEntityRoute,
    ...fieldTestServiceImplEntityPopupRoute,
];

@NgModule({
    imports: [
        Jh4TestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FieldTestServiceImplEntityComponent,
        FieldTestServiceImplEntityDetailComponent,
        FieldTestServiceImplEntityDialogComponent,
        FieldTestServiceImplEntityDeleteDialogComponent,
        FieldTestServiceImplEntityPopupComponent,
        FieldTestServiceImplEntityDeletePopupComponent,
    ],
    entryComponents: [
        FieldTestServiceImplEntityComponent,
        FieldTestServiceImplEntityDialogComponent,
        FieldTestServiceImplEntityPopupComponent,
        FieldTestServiceImplEntityDeleteDialogComponent,
        FieldTestServiceImplEntityDeletePopupComponent,
    ],
    providers: [
        FieldTestServiceImplEntityService,
        FieldTestServiceImplEntityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestFieldTestServiceImplEntityModule {}
