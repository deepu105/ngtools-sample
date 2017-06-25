import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceImplAndDTO } from './entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from './entity-with-service-impl-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-impl-and-dto-detail',
    templateUrl: './entity-with-service-impl-and-dto-detail.component.html'
})
export class EntityWithServiceImplAndDTODetailComponent implements OnInit, OnDestroy {

    entityWithServiceImplAndDTO: EntityWithServiceImplAndDTO;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceImplAndDTOS();
    }

    load(id) {
        this.entityWithServiceImplAndDTOService.find(id).subscribe((entityWithServiceImplAndDTO) => {
            this.entityWithServiceImplAndDTO = entityWithServiceImplAndDTO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceImplAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceImplAndDTOListModification',
            (response) => this.load(this.entityWithServiceImplAndDTO.id)
        );
    }
}
