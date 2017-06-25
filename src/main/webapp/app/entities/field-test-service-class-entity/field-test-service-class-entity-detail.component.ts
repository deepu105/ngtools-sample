import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestServiceClassEntity } from './field-test-service-class-entity.model';
import { FieldTestServiceClassEntityService } from './field-test-service-class-entity.service';

@Component({
    selector: 'jhi-field-test-service-class-entity-detail',
    templateUrl: './field-test-service-class-entity-detail.component.html'
})
export class FieldTestServiceClassEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestServiceClassEntity: FieldTestServiceClassEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestServiceClassEntityService: FieldTestServiceClassEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestServiceClassEntities();
    }

    load(id) {
        this.fieldTestServiceClassEntityService.find(id).subscribe((fieldTestServiceClassEntity) => {
            this.fieldTestServiceClassEntity = fieldTestServiceClassEntity;
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

    registerChangeInFieldTestServiceClassEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestServiceClassEntityListModification',
            (response) => this.load(this.fieldTestServiceClassEntity.id)
        );
    }
}
