import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithServiceClassPaginationAndDTOComponent } from './entity-with-service-class-pagination-and-dto.component';
import { EntityWithServiceClassPaginationAndDTODetailComponent } from './entity-with-service-class-pagination-and-dto-detail.component';
import { EntityWithServiceClassPaginationAndDTOPopupComponent } from './entity-with-service-class-pagination-and-dto-dialog.component';
import {
    EntityWithServiceClassPaginationAndDTODeletePopupComponent
} from './entity-with-service-class-pagination-and-dto-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EntityWithServiceClassPaginationAndDTOResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const entityWithServiceClassPaginationAndDTORoute: Routes = [
    {
        path: 'entity-with-service-class-pagination-and-dto',
        component: EntityWithServiceClassPaginationAndDTOComponent,
        resolve: {
            'pagingParams': EntityWithServiceClassPaginationAndDTOResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-service-class-pagination-and-dto/:id',
        component: EntityWithServiceClassPaginationAndDTODetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithServiceClassPaginationAndDTOPopupRoute: Routes = [
    {
        path: 'entity-with-service-class-pagination-and-dto-new',
        component: EntityWithServiceClassPaginationAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-class-pagination-and-dto/:id/edit',
        component: EntityWithServiceClassPaginationAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-class-pagination-and-dto/:id/delete',
        component: EntityWithServiceClassPaginationAndDTODeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceClassPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
