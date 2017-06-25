import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithServiceImplAndPagination } from './entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationPopupService } from './entity-with-service-impl-and-pagination-popup.service';
import { EntityWithServiceImplAndPaginationService } from './entity-with-service-impl-and-pagination.service';

@Component({
    selector: 'jhi-entity-with-service-impl-and-pagination-dialog',
    templateUrl: './entity-with-service-impl-and-pagination-dialog.component.html'
})
export class EntityWithServiceImplAndPaginationDialogComponent implements OnInit {

    entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithServiceImplAndPaginationService: EntityWithServiceImplAndPaginationService,
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
        if (this.entityWithServiceImplAndPagination.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithServiceImplAndPaginationService.update(this.entityWithServiceImplAndPagination));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithServiceImplAndPaginationService.create(this.entityWithServiceImplAndPagination));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithServiceImplAndPagination>) {
        result.subscribe((res: EntityWithServiceImplAndPagination) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithServiceImplAndPagination) {
        this.eventManager.broadcast({ name: 'entityWithServiceImplAndPaginationListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-service-impl-and-pagination-popup',
    template: ''
})
export class EntityWithServiceImplAndPaginationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplAndPaginationPopupService: EntityWithServiceImplAndPaginationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithServiceImplAndPaginationPopupService
                    .open(EntityWithServiceImplAndPaginationDialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithServiceImplAndPaginationPopupService
                    .open(EntityWithServiceImplAndPaginationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
