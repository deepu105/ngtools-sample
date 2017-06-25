import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceImplAndDTO } from './entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOPopupService } from './entity-with-service-impl-and-dto-popup.service';
import { EntityWithServiceImplAndDTOService } from './entity-with-service-impl-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-impl-and-dto-dialog',
    templateUrl: './entity-with-service-impl-and-dto-dialog.component.html'
})
export class EntityWithServiceImplAndDTODialogComponent implements OnInit {

    entityWithServiceImplAndDTO: EntityWithServiceImplAndDTO;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService,
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
        if (this.entityWithServiceImplAndDTO.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithServiceImplAndDTOService.update(this.entityWithServiceImplAndDTO));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithServiceImplAndDTOService.create(this.entityWithServiceImplAndDTO));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithServiceImplAndDTO>) {
        result.subscribe((res: EntityWithServiceImplAndDTO) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithServiceImplAndDTO) {
        this.eventManager.broadcast({ name: 'entityWithServiceImplAndDTOListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-service-impl-and-dto-popup',
    template: ''
})
export class EntityWithServiceImplAndDTOPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplAndDTOPopupService: EntityWithServiceImplAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithServiceImplAndDTOPopupService
                    .open(EntityWithServiceImplAndDTODialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithServiceImplAndDTOPopupService
                    .open(EntityWithServiceImplAndDTODialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
