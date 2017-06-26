import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Division } from './division.model';
import { DivisionService } from './division.service';

@Component({
    selector: 'jhi-division-detail',
    templateUrl: './division-detail.component.html'
})
export class DivisionDetailComponent implements OnInit, OnDestroy {

    division: Division;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private divisionService: DivisionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDivisions();
    }

    load(id) {
        this.divisionService.find(id).subscribe((division) => {
            this.division = division;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDivisions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'divisionListModification',
            (response) => this.load(this.division.id)
        );
    }
}
