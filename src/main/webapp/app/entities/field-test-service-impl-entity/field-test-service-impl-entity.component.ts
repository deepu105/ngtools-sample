import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestServiceImplEntity } from './field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from './field-test-service-impl-entity.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-field-test-service-impl-entity',
    templateUrl: './field-test-service-impl-entity.component.html'
})
export class FieldTestServiceImplEntityComponent implements OnInit, OnDestroy {
fieldTestServiceImplEntities: FieldTestServiceImplEntity[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private fieldTestServiceImplEntityService: FieldTestServiceImplEntityService,
        private alertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.fieldTestServiceImplEntityService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.fieldTestServiceImplEntities = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.fieldTestServiceImplEntityService.query().subscribe(
            (res: ResponseWrapper) => {
                this.fieldTestServiceImplEntities = res.json;
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
        this.registerChangeInFieldTestServiceImplEntities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: FieldTestServiceImplEntity) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInFieldTestServiceImplEntities() {
        this.eventSubscriber = this.eventManager.subscribe('fieldTestServiceImplEntityListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
