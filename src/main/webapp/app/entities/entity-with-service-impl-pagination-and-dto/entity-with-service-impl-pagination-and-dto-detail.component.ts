import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceImplPaginationAndDTO } from './entity-with-service-impl-pagination-and-dto.model';
import { EntityWithServiceImplPaginationAndDTOService } from './entity-with-service-impl-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-impl-pagination-and-dto-detail',
    templateUrl: './entity-with-service-impl-pagination-and-dto-detail.component.html'
})
export class EntityWithServiceImplPaginationAndDTODetailComponent implements OnInit, OnDestroy {

    entityWithServiceImplPaginationAndDTO: EntityWithServiceImplPaginationAndDTO;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceImplPaginationAndDTOService: EntityWithServiceImplPaginationAndDTOService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceImplPaginationAndDTOS();
    }

    load(id) {
        this.entityWithServiceImplPaginationAndDTOService.find(id).subscribe((entityWithServiceImplPaginationAndDTO) => {
            this.entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceImplPaginationAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceImplPaginationAndDTOListModification',
            (response) => this.load(this.entityWithServiceImplPaginationAndDTO.id)
        );
    }
}
