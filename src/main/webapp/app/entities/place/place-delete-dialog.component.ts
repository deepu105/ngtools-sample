import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Place } from './place.model';
import { PlacePopupService } from './place-popup.service';
import { PlaceService } from './place.service';

@Component({
    selector: 'jhi-place-delete-dialog',
    templateUrl: './place-delete-dialog.component.html'
})
export class PlaceDeleteDialogComponent {

    place: Place;

    constructor(
        private placeService: PlaceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.placeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'placeListModification',
                content: 'Deleted an place'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-place-delete-popup',
    template: ''
})
export class PlaceDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private placePopupService: PlacePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.placePopupService
                .open(PlaceDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
