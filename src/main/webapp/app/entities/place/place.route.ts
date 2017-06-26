import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PlaceComponent } from './place.component';
import { PlaceDetailComponent } from './place-detail.component';
import { PlacePopupComponent } from './place-dialog.component';
import { PlaceDeletePopupComponent } from './place-delete-dialog.component';

import { Principal } from '../../shared';

export const placeRoute: Routes = [
    {
        path: 'place',
        component: PlaceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.place.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'place/:id',
        component: PlaceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.place.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const placePopupRoute: Routes = [
    {
        path: 'place-new',
        component: PlacePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.place.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'place/:id/edit',
        component: PlacePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.place.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'place/:id/delete',
        component: PlaceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.place.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
