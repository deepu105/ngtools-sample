import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestInfiniteScrollEntity } from './field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityService } from './field-test-infinite-scroll-entity.service';

@Component({
    selector: 'jhi-field-test-infinite-scroll-entity-detail',
    templateUrl: './field-test-infinite-scroll-entity-detail.component.html'
})
export class FieldTestInfiniteScrollEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestInfiniteScrollEntityService: FieldTestInfiniteScrollEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestInfiniteScrollEntities();
    }

    load(id) {
        this.fieldTestInfiniteScrollEntityService.find(id).subscribe((fieldTestInfiniteScrollEntity) => {
            this.fieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntity;
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

    registerChangeInFieldTestInfiniteScrollEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestInfiniteScrollEntityListModification',
            (response) => this.load(this.fieldTestInfiniteScrollEntity.id)
        );
    }
}
