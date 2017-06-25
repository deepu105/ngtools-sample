import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FieldTestServiceImplEntityComponent } from './field-test-service-impl-entity.component';
import { FieldTestServiceImplEntityDetailComponent } from './field-test-service-impl-entity-detail.component';
import { FieldTestServiceImplEntityPopupComponent } from './field-test-service-impl-entity-dialog.component';
import { FieldTestServiceImplEntityDeletePopupComponent } from './field-test-service-impl-entity-delete-dialog.component';

import { Principal } from '../../shared';

export const fieldTestServiceImplEntityRoute: Routes = [
    {
        path: 'field-test-service-impl-entity',
        component: FieldTestServiceImplEntityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestServiceImplEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'field-test-service-impl-entity/:id',
        component: FieldTestServiceImplEntityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestServiceImplEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldTestServiceImplEntityPopupRoute: Routes = [
    {
        path: 'field-test-service-impl-entity-new',
        component: FieldTestServiceImplEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestServiceImplEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-service-impl-entity/:id/edit',
        component: FieldTestServiceImplEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestServiceImplEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-service-impl-entity/:id/delete',
        component: FieldTestServiceImplEntityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestServiceImplEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
