import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithDTOComponent } from './entity-with-dto.component';
import { EntityWithDTODetailComponent } from './entity-with-dto-detail.component';
import { EntityWithDTOPopupComponent } from './entity-with-dto-dialog.component';
import { EntityWithDTODeletePopupComponent } from './entity-with-dto-delete-dialog.component';

import { Principal } from '../../shared';

export const entityWithDTORoute: Routes = [
    {
        path: 'entity-with-dto',
        component: EntityWithDTOComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-dto/:id',
        component: EntityWithDTODetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithDTOPopupRoute: Routes = [
    {
        path: 'entity-with-dto-new',
        component: EntityWithDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-dto/:id/edit',
        component: EntityWithDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-dto/:id/delete',
        component: EntityWithDTODeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
