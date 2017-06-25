import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceClassAndPagination } from './entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationPopupService } from './entity-with-service-class-and-pagination-popup.service';
import { EntityWithServiceClassAndPaginationService } from './entity-with-service-class-and-pagination.service';

@Component({
    selector: 'jhi-entity-with-service-class-and-pagination-dialog',
    templateUrl: './entity-with-service-class-and-pagination-dialog.component.html'
})
export class EntityWithServiceClassAndPaginationDialogComponent implements OnInit {

    entityWithServiceClassAndPagination: EntityWithServiceClassAndPagination;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithServiceClassAndPaginationService: EntityWithServiceClassAndPaginationService,
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
        if (this.entityWithServiceClassAndPagination.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithServiceClassAndPaginationService.update(this.entityWithServiceClassAndPagination));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithServiceClassAndPaginationService.create(this.entityWithServiceClassAndPagination));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithServiceClassAndPagination>) {
        result.subscribe((res: EntityWithServiceClassAndPagination) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithServiceClassAndPagination) {
        this.eventManager.broadcast({ name: 'entityWithServiceClassAndPaginationListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-service-class-and-pagination-popup',
    template: ''
})
export class EntityWithServiceClassAndPaginationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassAndPaginationPopupService: EntityWithServiceClassAndPaginationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithServiceClassAndPaginationPopupService
                    .open(EntityWithServiceClassAndPaginationDialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithServiceClassAndPaginationPopupService
                    .open(EntityWithServiceClassAndPaginationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
