import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Place } from './place.model';
import { PlaceService } from './place.service';

@Component({
    selector: 'jhi-place-detail',
    templateUrl: './place-detail.component.html'
})
export class PlaceDetailComponent implements OnInit, OnDestroy {

    place: Place;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private placeService: PlaceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPlaces();
    }

    load(id) {
        this.placeService.find(id).subscribe((place) => {
            this.place = place;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPlaces() {
        this.eventSubscriber = this.eventManager.subscribe(
            'placeListModification',
            (response) => this.load(this.place.id)
        );
    }
}
