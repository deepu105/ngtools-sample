import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { EntityWithServiceImpl } from './entity-with-service-impl.model';
import { EntityWithServiceImplService } from './entity-with-service-impl.service';

@Component({
    selector: 'jhi-entity-with-service-impl-detail',
    templateUrl: './entity-with-service-impl-detail.component.html'
})
export class EntityWithServiceImplDetailComponent implements OnInit, OnDestroy {

    entityWithServiceImpl: EntityWithServiceImpl;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entityWithServiceImplService: EntityWithServiceImplService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntityWithServiceImpls();
    }

    load(id) {
        this.entityWithServiceImplService.find(id).subscribe((entityWithServiceImpl) => {
            this.entityWithServiceImpl = entityWithServiceImpl;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntityWithServiceImpls() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entityWithServiceImplListModification',
            (response) => this.load(this.entityWithServiceImpl.id)
        );
    }
}
