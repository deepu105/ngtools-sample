import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceClassPaginationAndDTO } from './entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOPopupService } from './entity-with-service-class-pagination-and-dto-popup.service';
import { EntityWithServiceClassPaginationAndDTOService } from './entity-with-service-class-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-class-pagination-and-dto-dialog',
    templateUrl: './entity-with-service-class-pagination-and-dto-dialog.component.html'
})
export class EntityWithServiceClassPaginationAndDTODialogComponent implements OnInit {

    entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithServiceClassPaginationAndDTOService: EntityWithServiceClassPaginationAndDTOService,
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
        if (this.entityWithServiceClassPaginationAndDTO.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithServiceClassPaginationAndDTOService.update(this.entityWithServiceClassPaginationAndDTO));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithServiceClassPaginationAndDTOService.create(this.entityWithServiceClassPaginationAndDTO));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithServiceClassPaginationAndDTO>) {
        result.subscribe((res: EntityWithServiceClassPaginationAndDTO) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithServiceClassPaginationAndDTO) {
        this.eventManager.broadcast({ name: 'entityWithServiceClassPaginationAndDTOListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-service-class-pagination-and-dto-popup',
    template: ''
})
export class EntityWithServiceClassPaginationAndDTOPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassPaginationAndDTOPopupService: EntityWithServiceClassPaginationAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithServiceClassPaginationAndDTOPopupService
                    .open(EntityWithServiceClassPaginationAndDTODialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithServiceClassPaginationAndDTOPopupService
                    .open(EntityWithServiceClassPaginationAndDTODialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
