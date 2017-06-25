import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestEntity } from './field-test-entity.model';
import { FieldTestEntityService } from './field-test-entity.service';

@Component({
    selector: 'jhi-field-test-entity-detail',
    templateUrl: './field-test-entity-detail.component.html'
})
export class FieldTestEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestEntity: FieldTestEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestEntityService: FieldTestEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestEntities();
    }

    load(id) {
        this.fieldTestEntityService.find(id).subscribe((fieldTestEntity) => {
            this.fieldTestEntity = fieldTestEntity;
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

    registerChangeInFieldTestEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestEntityListModification',
            (response) => this.load(this.fieldTestEntity.id)
        );
    }
}
