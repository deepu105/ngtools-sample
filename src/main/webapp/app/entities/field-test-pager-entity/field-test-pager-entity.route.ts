import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FieldTestPagerEntityComponent } from './field-test-pager-entity.component';
import { FieldTestPagerEntityDetailComponent } from './field-test-pager-entity-detail.component';
import { FieldTestPagerEntityPopupComponent } from './field-test-pager-entity-dialog.component';
import { FieldTestPagerEntityDeletePopupComponent } from './field-test-pager-entity-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class FieldTestPagerEntityResolvePagingParams implements Resolve<any> {

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

export const fieldTestPagerEntityRoute: Routes = [
    {
        path: 'field-test-pager-entity',
        component: FieldTestPagerEntityComponent,
        resolve: {
            'pagingParams': FieldTestPagerEntityResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPagerEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'field-test-pager-entity/:id',
        component: FieldTestPagerEntityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPagerEntity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldTestPagerEntityPopupRoute: Routes = [
    {
        path: 'field-test-pager-entity-new',
        component: FieldTestPagerEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPagerEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-pager-entity/:id/edit',
        component: FieldTestPagerEntityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPagerEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'field-test-pager-entity/:id/delete',
        component: FieldTestPagerEntityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jh4TestApp.fieldTestPagerEntity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
