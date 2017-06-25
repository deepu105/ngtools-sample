import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceClassAndDTO } from './entity-with-service-class-and-dto.model';
import { EntityWithServiceClassAndDTOService } from './entity-with-service-class-and-dto.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-entity-with-service-class-and-dto',
    templateUrl: './entity-with-service-class-and-dto.component.html'
})
export class EntityWithServiceClassAndDTOComponent implements OnInit, OnDestroy {
entityWithServiceClassAndDTOS: EntityWithServiceClassAndDTO[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private entityWithServiceClassAndDTOService: EntityWithServiceClassAndDTOService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.entityWithServiceClassAndDTOService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.entityWithServiceClassAndDTOS = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.entityWithServiceClassAndDTOService.query().subscribe(
            (res: ResponseWrapper) => {
                this.entityWithServiceClassAndDTOS = res.json;
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
        this.registerChangeInEntityWithServiceClassAndDTOS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: EntityWithServiceClassAndDTO) {
        return item.id;
    }
    registerChangeInEntityWithServiceClassAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe('entityWithServiceClassAndDTOListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
