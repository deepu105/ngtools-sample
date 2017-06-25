import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceClass } from './entity-with-service-class.model';
import { EntityWithServiceClassService } from './entity-with-service-class.service';

@Component({
    selector: 'jhi-entity-with-service-class-detail',
    templateUrl: './entity-with-service-class-detail.component.html'
})
export class EntityWithServiceClassDetailComponent implements OnInit, OnDestroy {

    entityWithServiceClass: EntityWithServiceClass;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceClassService: EntityWithServiceClassService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceClasses();
    }

    load(id) {
        this.entityWithServiceClassService.find(id).subscribe((entityWithServiceClass) => {
            this.entityWithServiceClass = entityWithServiceClass;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceClasses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceClassListModification',
            (response) => this.load(this.entityWithServiceClass.id)
        );
    }
}
