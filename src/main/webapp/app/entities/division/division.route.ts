import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DivisionComponent } from './division.component';
import { DivisionDetailComponent } from './division-detail.component';
import { DivisionPopupComponent } from './division-dialog.component';
import { DivisionDeletePopupComponent } from './division-delete-dialog.component';

import { Principal } from '../../shared';

export const divisionRoute: Routes = [
    {
        path: 'division',
        component: DivisionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.division.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'division/:id',
        component: DivisionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.division.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const divisionPopupRoute: Routes = [
    {
        path: 'division-new',
        component: DivisionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.division.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'division/:id/edit',
        component: DivisionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.division.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'division/:id/delete',
        component: DivisionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.division.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
