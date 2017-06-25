import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithPaginationAndDTO } from './entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOService } from './entity-with-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-pagination-and-dto-detail',
    templateUrl: './entity-with-pagination-and-dto-detail.component.html'
})
export class EntityWithPaginationAndDTODetailComponent implements OnInit, OnDestroy {

    entityWithPaginationAndDTO: EntityWithPaginationAndDTO;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithPaginationAndDTOService: EntityWithPaginationAndDTOService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithPaginationAndDTOS();
    }

    load(id) {
        this.entityWithPaginationAndDTOService.find(id).subscribe((entityWithPaginationAndDTO) => {
            this.entityWithPaginationAndDTO = entityWithPaginationAndDTO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithPaginationAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithPaginationAndDTOListModification',
            (response) => this.load(this.entityWithPaginationAndDTO.id)
        );
    }
}
