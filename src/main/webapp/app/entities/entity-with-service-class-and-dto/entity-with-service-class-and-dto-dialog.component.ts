import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceClassAndDTO } from './entity-with-service-class-and-dto.model';
import { EntityWithServiceClassAndDTOPopupService } from './entity-with-service-class-and-dto-popup.service';
import { EntityWithServiceClassAndDTOService } from './entity-with-service-class-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-class-and-dto-dialog',
    templateUrl: './entity-with-service-class-and-dto-dialog.component.html'
})
export class EntityWithServiceClassAndDTODialogComponent implements OnInit {

    entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithServiceClassAndDTOService: EntityWithServiceClassAndDTOService,
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
        if (this.entityWithServiceClassAndDTO.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithServiceClassAndDTOService.update(this.entityWithServiceClassAndDTO));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithServiceClassAndDTOService.create(this.entityWithServiceClassAndDTO));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithServiceClassAndDTO>) {
        result.subscribe((res: EntityWithServiceClassAndDTO) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithServiceClassAndDTO) {
        this.eventManager.broadcast({ name: 'entityWithServiceClassAndDTOListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-service-class-and-dto-popup',
    template: ''
})
export class EntityWithServiceClassAndDTOPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassAndDTOPopupService: EntityWithServiceClassAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithServiceClassAndDTOPopupService
                    .open(EntityWithServiceClassAndDTODialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithServiceClassAndDTOPopupService
                    .open(EntityWithServiceClassAndDTODialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
