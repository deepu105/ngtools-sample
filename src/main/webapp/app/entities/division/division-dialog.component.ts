import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Division } from './division.model';
import { DivisionPopupService } from './division-popup.service';
import { DivisionService } from './division.service';

@Component({
    selector: 'jhi-division-dialog',
    templateUrl: './division-dialog.component.html'
})
export class DivisionDialogComponent implements OnInit {

    division: Division;
    authorities: any[];
    isSaving: boolean;

    places: Place[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private divisionService: DivisionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.placeService.query()
            .subscribe((res: ResponseWrapper) => { this.places = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.division.id !== undefined) {
            this.subscribeToSaveResponse(
                this.divisionService.update(this.division));
        } else {
            this.subscribeToSaveResponse(
                this.divisionService.create(this.division));
        }
    }

    private subscribeToSaveResponse(result: Observable<Division>) {
        result.subscribe((res: Division) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Division) {
        this.eventManager.broadcast({ name: 'divisionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackPlaceById(index: number, item: Place) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-division-popup',
    template: ''
})
export class DivisionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private divisionPopupService: DivisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.divisionPopupService
                    .open(DivisionDialogComponent, params['id']);
            } else {
                this.modalRef = this.divisionPopupService
                    .open(DivisionDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
