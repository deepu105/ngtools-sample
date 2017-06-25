import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FieldTestInfiniteScrollEntityComponent } from './field-test-infinite-scroll-entity.component';
import { FieldTestInfiniteScrollEntityDetailComponent } from './field-test-infinite-scroll-entity-detail.component';
import { FieldTestInfiniteScrollEntityPopupComponent } from './field-test-infinite-scroll-entity-dialog.component';
import {
    FieldTestInfiniteScrollEntityDeletePopupComponent
} from './field-test-infinite-scroll-entity-delete-dialog.component';

import { Principal } from '../../shared';

export const fieldTestInfiniteScrollEntityRoute: Routes = [
    {
        path: 'field-test-infinite-scroll-entity',
        component: FieldTestInfiniteScrollEntityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestInfiniteScrollEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'field-test-infinite-scroll-entity/:id',
        component: FieldTestInfiniteScrollEntityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestInfiniteScrollEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldTestInfiniteScrollEntityPopupRoute: Routes = [
    {
        path: 'field-test-infinite-scroll-entity-new',
        component: FieldTestInfiniteScrollEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestInfiniteScrollEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-infinite-scroll-entity/:id/edit',
        component: FieldTestInfiniteScrollEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestInfiniteScrollEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-infinite-scroll-entity/:id/delete',
        component: FieldTestInfiniteScrollEntityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestInfiniteScrollEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
