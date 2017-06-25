import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceImplAndPagination } from './entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationService } from './entity-with-service-impl-and-pagination.service';

@Component({
    selector: 'jhi-entity-with-service-impl-and-pagination-detail',
    templateUrl: './entity-with-service-impl-and-pagination-detail.component.html'
})
export class EntityWithServiceImplAndPaginationDetailComponent implements OnInit, OnDestroy {

    entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceImplAndPaginationService: EntityWithServiceImplAndPaginationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceImplAndPaginations();
    }

    load(id) {
        this.entityWithServiceImplAndPaginationService.find(id).subscribe((entityWithServiceImplAndPagination) => {
            this.entityWithServiceImplAndPagination = entityWithServiceImplAndPagination;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceImplAndPaginations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceImplAndPaginationListModification',
            (response) => this.load(this.entityWithServiceImplAndPagination.id)
        );
    }
}
