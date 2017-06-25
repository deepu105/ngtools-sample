import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestPaginationEntity } from './field-test-pagination-entity.model';
import { FieldTestPaginationEntityService } from './field-test-pagination-entity.service';

@Component({
    selector: 'jhi-field-test-pagination-entity-detail',
    templateUrl: './field-test-pagination-entity-detail.component.html'
})
export class FieldTestPaginationEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestPaginationEntity: FieldTestPaginationEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestPaginationEntityService: FieldTestPaginationEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestPaginationEntities();
    }

    load(id) {
        this.fieldTestPaginationEntityService.find(id).subscribe((fieldTestPaginationEntity) => {
            this.fieldTestPaginationEntity = fieldTestPaginationEntity;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFieldTestPaginationEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestPaginationEntityListModification',
            (response) => this.load(this.fieldTestPaginationEntity.id)
        );
    }
}
