import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FieldTestMapstructEntityComponent } from './field-test-mapstruct-entity.component';
import { FieldTestMapstructEntityDetailComponent } from './field-test-mapstruct-entity-detail.component';
import { FieldTestMapstructEntityPopupComponent } from './field-test-mapstruct-entity-dialog.component';
import { FieldTestMapstructEntityDeletePopupComponent } from './field-test-mapstruct-entity-delete-dialog.component';

import { Principal } from '../../shared';

export const fieldTestMapstructEntityRoute: Routes = [
    {
        path: 'field-test-mapstruct-entity',
        component: FieldTestMapstructEntityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestMapstructEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'field-test-mapstruct-entity/:id',
        component: FieldTestMapstructEntityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestMapstructEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldTestMapstructEntityPopupRoute: Routes = [
    {
        path: 'field-test-mapstruct-entity-new',
        component: FieldTestMapstructEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestMapstructEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-mapstruct-entity/:id/edit',
        component: FieldTestMapstructEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestMapstructEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-mapstruct-entity/:id/delete',
        component: FieldTestMapstructEntityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestMapstructEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
