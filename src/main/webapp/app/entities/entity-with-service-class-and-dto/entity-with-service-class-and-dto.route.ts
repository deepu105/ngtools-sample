import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithServiceClassAndDTOComponent } from './entity-with-service-class-and-dto.component';
import { EntityWithServiceClassAndDTODetailComponent } from './entity-with-service-class-and-dto-detail.component';
import { EntityWithServiceClassAndDTOPopupComponent } from './entity-with-service-class-and-dto-dialog.component';
import {
    EntityWithServiceClassAndDTODeletePopupComponent
} from './entity-with-service-class-and-dto-delete-dialog.component';

import { Principal } from '../../shared';

export const entityWithServiceClassAndDTORoute: Routes = [
    {
        path: 'entity-with-service-class-and-dto',
        component: EntityWithServiceClassAndDTOComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-service-class-and-dto/:id',
        component: EntityWithServiceClassAndDTODetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithServiceClassAndDTOPopupRoute: Routes = [
    {
        path: 'entity-with-service-class-and-dto-new',
        component: EntityWithServiceClassAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-class-and-dto/:id/edit',
        component: EntityWithServiceClassAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-class-and-dto/:id/delete',
        component: EntityWithServiceClassAndDTODeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
