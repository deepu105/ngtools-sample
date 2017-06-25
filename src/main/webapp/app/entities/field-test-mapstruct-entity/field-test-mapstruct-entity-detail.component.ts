import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestMapstructEntity } from './field-test-mapstruct-entity.model';
import { FieldTestMapstructEntityService } from './field-test-mapstruct-entity.service';

@Component({
    selector: 'jhi-field-test-mapstruct-entity-detail',
    templateUrl: './field-test-mapstruct-entity-detail.component.html'
})
export class FieldTestMapstructEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestMapstructEntity: FieldTestMapstructEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestMapstructEntityService: FieldTestMapstructEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestMapstructEntities();
    }

    load(id) {
        this.fieldTestMapstructEntityService.find(id).subscribe((fieldTestMapstructEntity) => {
            this.fieldTestMapstructEntity = fieldTestMapstructEntity;
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

    registerChangeInFieldTestMapstructEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestMapstructEntityListModification',
            (response) => this.load(this.fieldTestMapstructEntity.id)
        );
    }
}
