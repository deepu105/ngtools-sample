import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithServiceImplAndDTOComponent } from './entity-with-service-impl-and-dto.component';
import { EntityWithServiceImplAndDTODetailComponent } from './entity-with-service-impl-and-dto-detail.component';
import { EntityWithServiceImplAndDTOPopupComponent } from './entity-with-service-impl-and-dto-dialog.component';
import {
    EntityWithServiceImplAndDTODeletePopupComponent
} from './entity-with-service-impl-and-dto-delete-dialog.component';

import { Principal } from '../../shared';

export const entityWithServiceImplAndDTORoute: Routes = [
    {
        path: 'entity-with-service-impl-and-dto',
        component: EntityWithServiceImplAndDTOComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-service-impl-and-dto/:id',
        component: EntityWithServiceImplAndDTODetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithServiceImplAndDTOPopupRoute: Routes = [
    {
        path: 'entity-with-service-impl-and-dto-new',
        component: EntityWithServiceImplAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-impl-and-dto/:id/edit',
        component: EntityWithServiceImplAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-impl-and-dto/:id/delete',
        component: EntityWithServiceImplAndDTODeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
