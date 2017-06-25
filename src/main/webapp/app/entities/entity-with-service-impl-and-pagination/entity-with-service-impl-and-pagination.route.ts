import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithServiceImplAndPaginationComponent } from './entity-with-service-impl-and-pagination.component';
import { EntityWithServiceImplAndPaginationDetailComponent } from './entity-with-service-impl-and-pagination-detail.component';
import { EntityWithServiceImplAndPaginationPopupComponent } from './entity-with-service-impl-and-pagination-dialog.component';
import {
    EntityWithServiceImplAndPaginationDeletePopupComponent
} from './entity-with-service-impl-and-pagination-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EntityWithServiceImplAndPaginationResolvePagingParams implements Resolve<any> {

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

export const entityWithServiceImplAndPaginationRoute: Routes = [
    {
        path: 'entity-with-service-impl-and-pagination',
        component: EntityWithServiceImplAndPaginationComponent,
        resolve: {
            'pagingParams': EntityWithServiceImplAndPaginationResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndPagination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-service-impl-and-pagination/:id',
        component: EntityWithServiceImplAndPaginationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndPagination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithServiceImplAndPaginationPopupRoute: Routes = [
    {
        path: 'entity-with-service-impl-and-pagination-new',
        component: EntityWithServiceImplAndPaginationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndPagination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-impl-and-pagination/:id/edit',
        component: EntityWithServiceImplAndPaginationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndPagination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-service-impl-and-pagination/:id/delete',
        component: EntityWithServiceImplAndPaginationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithServiceImplAndPagination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
