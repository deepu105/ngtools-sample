import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceImpl } from './entity-with-service-impl.model';
import { EntityWithServiceImplService } from './entity-with-service-impl.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-entity-with-service-impl',
    templateUrl: './entity-with-service-impl.component.html'
})
export class EntityWithServiceImplComponent implements OnInit, OnDestroy {
entityWithServiceImpls: EntityWithServiceImpl[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private entityWithServiceImplService: EntityWithServiceImplService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.entityWithServiceImplService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.entityWithServiceImpls = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.entityWithServiceImplService.query().subscribe(
            (res: ResponseWrapper) => {
                this.entityWithServiceImpls = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInEntityWithServiceImpls();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: EntityWithServiceImpl) {
        return item.id;
    }
    registerChangeInEntityWithServiceImpls() {
        this.eventSubscriber = this.eventManager.subscribe('entityWithServiceImplListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
