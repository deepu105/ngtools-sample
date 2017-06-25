import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { EntityWithPaginationComponent } from './entity-with-pagination.component';
import { EntityWithPaginationDetailComponent } from './entity-with-pagination-detail.component';
import { EntityWithPaginationPopupComponent } from './entity-with-pagination-dialog.component';
import { EntityWithPaginationDeletePopupComponent } from './entity-with-pagination-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EntityWithPaginationResolvePagingParams implements Resolve<any> {

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

export const entityWithPaginationRoute: Routes = [
    {
        path: 'entity-with-pagination',
        component: EntityWithPaginationComponent,
        resolve: {
            'pagingParams': EntityWithPaginationResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPagination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entity-with-pagination/:id',
        component: EntityWithPaginationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPagination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityWithPaginationPopupRoute: Routes = [
    {
        path: 'entity-with-pagination-new',
        component: EntityWithPaginationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPagination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-pagination/:id/edit',
        component: EntityWithPaginationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPagination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entity-with-pagination/:id/delete',
        component: EntityWithPaginationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.entityWithPagination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
