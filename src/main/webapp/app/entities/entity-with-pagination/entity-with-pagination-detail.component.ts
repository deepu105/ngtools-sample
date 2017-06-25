import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithPagination } from './entity-with-pagination.model';
import { EntityWithPaginationService } from './entity-with-pagination.service';

@Component({
    selector: 'jhi-entity-with-pagination-detail',
    templateUrl: './entity-with-pagination-detail.component.html'
})
export class EntityWithPaginationDetailComponent implements OnInit, OnDestroy {

    entityWithPagination: EntityWithPagination;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithPaginationService: EntityWithPaginationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithPaginations();
    }

    load(id) {
        this.entityWithPaginationService.find(id).subscribe((entityWithPagination) => {
            this.entityWithPagination = entityWithPagination;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithPaginations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithPaginationListModification',
            (response) => this.load(this.entityWithPagination.id)
        );
    }
}
