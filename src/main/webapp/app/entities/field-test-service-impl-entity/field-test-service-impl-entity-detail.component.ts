import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { FieldTestServiceImplEntity } from './field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from './field-test-service-impl-entity.service';

@Component({
    selector: 'jhi-field-test-service-impl-entity-detail',
    templateUrl: './field-test-service-impl-entity-detail.component.html'
})
export class FieldTestServiceImplEntityDetailComponent implements OnInit, OnDestroy {

    fieldTestServiceImplEntity: FieldTestServiceImplEntity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fieldTestServiceImplEntityService: FieldTestServiceImplEntityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFieldTestServiceImplEntities();
    }

    load(id) {
        this.fieldTestServiceImplEntityService.find(id).subscribe((fieldTestServiceImplEntity) => {
            this.fieldTestServiceImplEntity = fieldTestServiceImplEntity;
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

    registerChangeInFieldTestServiceImplEntities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fieldTestServiceImplEntityListModification',
            (response) => this.load(this.fieldTestServiceImplEntity.id)
        );
    }
}
