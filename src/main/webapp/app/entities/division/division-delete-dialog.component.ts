import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Division } from './division.model';
import { DivisionPopupService } from './division-popup.service';
import { DivisionService } from './division.service';

@Component({
    selector: 'jhi-division-delete-dialog',
    templateUrl: './division-delete-dialog.component.html'
})
export class DivisionDeleteDialogComponent {

    division: Division;

    constructor(
        private divisionService: DivisionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.divisionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'divisionListModification',
                content: 'Deleted an division'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-division-delete-popup',
    template: ''
})
export class DivisionDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private divisionPopupService: DivisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.divisionPopupService
                .open(DivisionDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
