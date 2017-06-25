import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceClassAndDTO } from './entity-with-service-class-and-dto.model';
import { EntityWithServiceClassAndDTOService } from './entity-with-service-class-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-class-and-dto-detail',
    templateUrl: './entity-with-service-class-and-dto-detail.component.html'
})
export class EntityWithServiceClassAndDTODetailComponent implements OnInit, OnDestroy {

    entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceClassAndDTOService: EntityWithServiceClassAndDTOService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceClassAndDTOS();
    }

    load(id) {
        this.entityWithServiceClassAndDTOService.find(id).subscribe((entityWithServiceClassAndDTO) => {
            this.entityWithServiceClassAndDTO = entityWithServiceClassAndDTO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceClassAndDTOS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceClassAndDTOListModification',
            (response) => this.load(this.entityWithServiceClassAndDTO.id)
        );
    }
}
