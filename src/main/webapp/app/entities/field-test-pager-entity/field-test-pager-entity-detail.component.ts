import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestPagerEntity } from './field-test-pager-entity.model';
import { FieldTestPagerEntityService } from './field-test-pager-entity.service';

@Component({
    selector: 'jhi-field-test-pager-entity-detail',
    templateUrl: './field-test-pager-entity-detail.component.html'
})
export class FieldTestPagerEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestPagerEntity: FieldTestPagerEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestPagerEntityService: FieldTestPagerEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestPagerEntities();
    }

    load(id) {
        this.fieldTestPagerEntityService.find(id).subscribe((fieldTestPagerEntity) => {
            this.fieldTestPagerEntity = fieldTestPagerEntity;
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

    registerChangeInFieldTestPagerEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestPagerEntityListModification',
            (response) => this.load(this.fieldTestPagerEntity.id)
        );
    }
}
