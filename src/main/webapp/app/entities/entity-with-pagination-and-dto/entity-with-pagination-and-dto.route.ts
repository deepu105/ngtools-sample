import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithPaginationAndDTOComponent } from './entity-with-pagination-and-dto.component';
import { EntityWithPaginationAndDTODetailComponent } from './entity-with-pagination-and-dto-detail.component';
import { EntityWithPaginationAndDTOPopupComponent } from './entity-with-pagination-and-dto-dialog.component';
import { EntityWithPaginationAndDTODeletePopupComponent } from './entity-with-pagination-and-dto-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EntityWithPaginationAndDTOResolvePagingParams implements Resolve<any> {

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

export const entityWithPaginationAndDTORoute: Routes = [
    {
        path: 'entity-with-pagination-and-dto',
        component: EntityWithPaginationAndDTOComponent,
        resolve: {
            'pagingParams': EntityWithPaginationAndDTOResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-pagination-and-dto/:id',
        component: EntityWithPaginationAndDTODetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithPaginationAndDTOPopupRoute: Routes = [
    {
        path: 'entity-with-pagination-and-dto-new',
        component: EntityWithPaginationAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-pagination-and-dto/:id/edit',
        component: EntityWithPaginationAndDTOPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-pagination-and-dto/:id/delete',
        component: EntityWithPaginationAndDTODeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPaginationAndDTO.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
