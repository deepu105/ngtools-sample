import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithDTO } from './entity-with-dto.model';
import { EntityWithDTOService } from './entity-with-dto.service';

@Component({
    selector: 'jhi-entity-with-dto-detail',
    templateUrl: './entity-with-dto-detail.component.html'
})
export class EntityWithDTODetailComponent implements OnInit, OnDestroy {

    entityWithDTO: EntityWithDTO;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithDTOService: EntityWithDTOService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithDTOS();
    }

    load(id) {
        this.entityWithDTOService.find(id).subscribe((entityWithDTO) => {
            this.entityWithDTO = entityWithDTO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithDTOS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithDTOListModification',
            (response) => this.load(this.entityWithDTO.id)
        );
    }
}
