import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceImplAndDTO } from './entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from './entity-with-service-impl-and-dto.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-entity-with-service-impl-and-dto',
    templateUrl: './entity-with-service-impl-and-dto.component.html'
})
export class EntityWithServiceImplAndDTOComponent implements OnInit, OnDestroy {
entityWithServiceImplAndDTOS: EntityWithServiceImplAndDTO[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.entityWithServiceImplAndDTOService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.entityWithServiceImplAndDTOS = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.entityWithServiceImplAndDTOService.query().subscribe(
            (res: ResponseWrapper) => {
                this.entityWithServiceImplAndDTOS = res.json;
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
        this.registerChangeInEntityWithServiceImplAndDTOS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: EntityWithServiceImplAndDTO) {
        return item.id;
    }
    registerChangeInEntityWithServiceImplAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe('entityWithServiceImplAndDTOListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
