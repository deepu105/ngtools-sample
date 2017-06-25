import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntityWithPagination } from './entity-with-pagination.model';
import { EntityWithPaginationPopupService } from './entity-with-pagination-popup.service';
import { EntityWithPaginationService } from './entity-with-pagination.service';

@Component({
    selector: 'jhi-entity-with-pagination-dialog',
    templateUrl: './entity-with-pagination-dialog.component.html'
})
export class EntityWithPaginationDialogComponent implements OnInit {

    entityWithPagination: EntityWithPagination;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private entityWithPaginationService: EntityWithPaginationService,
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
        if (this.entityWithPagination.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entityWithPaginationService.update(this.entityWithPagination));
        } else {
            this.subscribeToSaveResponse(
                this.entityWithPaginationService.create(this.entityWithPagination));
        }
    }

    private subscribeToSaveResponse(result: Observable<EntityWithPagination>) {
        result.subscribe((res: EntityWithPagination) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: EntityWithPagination) {
        this.eventManager.broadcast({ name: 'entityWithPaginationListModification', content: 'OK'});
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
    selector: 'jhi-entity-with-pagination-popup',
    template: ''
})
export class EntityWithPaginationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithPaginationPopupService: EntityWithPaginationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entityWithPaginationPopupService
                    .open(EntityWithPaginationDialogComponent, params['id']);
            } else {
                this.modalRef = this.entityWithPaginationPopupService
                    .open(EntityWithPaginationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
