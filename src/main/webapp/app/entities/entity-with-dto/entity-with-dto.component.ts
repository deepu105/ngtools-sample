import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { EntityWithDTO } from './entity-with-dto.model';
import { EntityWithDTOService } from './entity-with-dto.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-entity-with-dto',
    templateUrl: './entity-with-dto.component.html'
})
export class EntityWithDTOComponent implements OnInit, OnDestroy {
entityWithDTOS: EntityWithDTO[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private entityWithDTOService: EntityWithDTOService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.entityWithDTOService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.entityWithDTOS = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.entityWithDTOService.query().subscribe(
            (res: ResponseWrapper) => {
                this.entityWithDTOS = res.json;
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
        this.registerChangeInEntityWithDTOS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: EntityWithDTO) {
        return item.id;
    }
    registerChangeInEntityWithDTOS() {
        this.eventSubscriber = this.eventManager.subscribe('entityWithDTOListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
