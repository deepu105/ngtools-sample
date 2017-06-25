import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FieldTestPaginationEntityComponent } from './field-test-pagination-entity.component';
import { FieldTestPaginationEntityDetailComponent } from './field-test-pagination-entity-detail.component';
import { FieldTestPaginationEntityPopupComponent } from './field-test-pagination-entity-dialog.component';
import { FieldTestPaginationEntityDeletePopupComponent } from './field-test-pagination-entity-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class FieldTestPaginationEntityResolvePagingParams implements Resolve<any> {

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

export const fieldTestPaginationEntityRoute: Routes = [
    {
        path: 'field-test-pagination-entity',
        component: FieldTestPaginationEntityComponent,
        resolve: {
            'pagingParams': FieldTestPaginationEntityResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPaginationEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'field-test-pagination-entity/:id',
        component: FieldTestPaginationEntityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPaginationEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldTestPaginationEntityPopupRoute: Routes = [
    {
        path: 'field-test-pagination-entity-new',
        component: FieldTestPaginationEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPaginationEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-pagination-entity/:id/edit',
        component: FieldTestPaginationEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPaginationEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-pagination-entity/:id/delete',
        component: FieldTestPaginationEntityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPaginationEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
