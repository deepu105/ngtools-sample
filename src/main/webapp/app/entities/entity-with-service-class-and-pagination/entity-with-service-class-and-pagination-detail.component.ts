import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceClassAndPagination } from './entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationService } from './entity-with-service-class-and-pagination.service';

@Component({
    selector: 'jhi-entity-with-service-class-and-pagination-detail',
    templateUrl: './entity-with-service-class-and-pagination-detail.component.html'
})
export class EntityWithServiceClassAndPaginationDetailComponent implements OnInit, OnDestroy {

    entityWithServiceClassAndPagination: EntityWithServiceClassAndPagination;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceClassAndPaginationService: EntityWithServiceClassAndPaginationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceClassAndPaginations();
    }

    load(id) {
        this.entityWithServiceClassAndPaginationService.find(id).subscribe((entityWithServiceClassAndPagination) => {
            this.entityWithServiceClassAndPagination = entityWithServiceClassAndPagination;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceClassAndPaginations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceClassAndPaginationListModification',
            (response) => this.load(this.entityWithServiceClassAndPagination.id)
        );
    }
}
