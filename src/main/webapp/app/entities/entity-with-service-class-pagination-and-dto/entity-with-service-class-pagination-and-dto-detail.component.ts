import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceClassPaginationAndDTO } from './entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOService } from './entity-with-service-class-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-class-pagination-and-dto-detail',
    templateUrl: './entity-with-service-class-pagination-and-dto-detail.component.html'
})
export class EntityWithServiceClassPaginationAndDTODetailComponent implements OnInit, OnDestroy {

    entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceClassPaginationAndDTOService: EntityWithServiceClassPaginationAndDTOService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceClassPaginationAndDTOS();
    }

    load(id) {
        this.entityWithServiceClassPaginationAndDTOService.find(id).subscribe((entityWithServiceClassPaginationAndDTO) => {
            this.entityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceClassPaginationAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceClassPaginationAndDTOListModification',
            (response) => this.load(this.entityWithServiceClassPaginationAndDTO.id)
        );
    }
}
