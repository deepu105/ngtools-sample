import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithServiceImplComponent } from './entity-with-service-impl.component';
import { EntityWithServiceImplDetailComponent } from './entity-with-service-impl-detail.component';
import { EntityWithServiceImplPopupComponent } from './entity-with-service-impl-dialog.component';
import { EntityWithServiceImplDeletePopupComponent } from './entity-with-service-impl-delete-dialog.component';

import { Principal } from '../../shared';

export const entityWithServiceImplRoute: Routes = [
    {
        path: 'entity-with-service-impl',
        component: EntityWithServiceImplComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImpl.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-service-impl/:id',
        component: EntityWithServiceImplDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImpl.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithServiceImplPopupRoute: Routes = [
    {
        path: 'entity-with-service-impl-new',
        component: EntityWithServiceImplPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImpl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-impl/:id/edit',
        component: EntityWithServiceImplPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImpl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-impl/:id/delete',
        component: EntityWithServiceImplDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImpl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
