import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithPaginationAndDTO } from './entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOPopupService } from './entity-with-pagination-and-dto-popup.service';
import { EntityWithPaginationAndDTOService } from './entity-with-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-pagination-and-dto-dialog',
    templateUrl: './entity-with-pagination-and-dto-dialog.component.html'
})
export class EntityWithPaginationAndDTODialogComponent implements OnInit {

    entityWithPaginationAndDTO: EntityWithPaginationAndDTO;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithPaginationAndDTOService: EntityWithPaginationAndDTOService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.entityWithPaginationAndDTO.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithPaginationAndDTOService.update(this.entityWithPaginationAndDTO));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithPaginationAndDTOService.create(this.entityWithPaginationAndDTO));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithPaginationAndDTO>) {
        result.subscribe((res: EntityWithPaginationAndDTO) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithPaginationAndDTO) {
        this.eventManager.broadcast({ name: 'entityWithPaginationAndDTOListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-entity-with-pagination-and-dto-popup',
    template: ''
})
export class EntityWithPaginationAndDTOPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithPaginationAndDTOPopupService: EntityWithPaginationAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithPaginationAndDTOPopupService
                    .open(EntityWithPaginationAndDTODialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithPaginationAndDTOPopupService
                    .open(EntityWithPaginationAndDTODialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
