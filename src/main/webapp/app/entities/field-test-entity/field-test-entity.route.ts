import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FieldTestEntityComponent } from './field-test-entity.component';
import { FieldTestEntityDetailComponent } from './field-test-entity-detail.component';
import { FieldTestEntityPopupComponent } from './field-test-entity-dialog.component';
import { FieldTestEntityDeletePopupComponent } from './field-test-entity-delete-dialog.component';

import { Principal } from '../../shared';

export const fieldTestEntityRoute: Routes = [
    {
        path: 'field-test-entity',
        component: FieldTestEntityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'field-test-entity/:id',
        component: FieldTestEntityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldTestEntityPopupRoute: Routes = [
    {
        path: 'field-test-entity-new',
        component: FieldTestEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-entity/:id/edit',
        component: FieldTestEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-entity/:id/delete',
        component: FieldTestEntityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
