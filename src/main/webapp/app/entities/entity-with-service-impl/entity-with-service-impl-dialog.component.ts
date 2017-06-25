import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceImpl } from './entity-with-service-impl.model';
import { EntityWithServiceImplPopupService } from './entity-with-service-impl-popup.service';
import { EntityWithServiceImplService } from './entity-with-service-impl.service';

@Component({
    selector: 'jhi-entity-with-service-impl-dialog',
    templateUrl: './entity-with-service-impl-dialog.component.html'
})
export class EntityWithServiceImplDialogComponent implements OnInit {

    entityWithServiceImpl: EntityWithServiceImpl;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithServiceImplService: EntityWithServiceImplService,
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
        if (this.entityWithServiceImpl.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithServiceImplService.update(this.entityWithServiceImpl));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithServiceImplService.create(this.entityWithServiceImpl));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithServiceImpl>) {
        result.subscribe((res: EntityWithServiceImpl) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithServiceImpl) {
        this.eventManager.broadcast({ name: 'entityWithServiceImplListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-service-impl-popup',
    template: ''
})
export class EntityWithServiceImplPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplPopupService: EntityWithServiceImplPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithServiceImplPopupService
                    .open(EntityWithServiceImplDialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithServiceImplPopupService
                    .open(EntityWithServiceImplDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
