import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestPaginationEntity } from './field-test-pagination-entity.model';
import { FieldTestPaginationEntityPopupService } from './field-test-pagination-entity-popup.service';
import { FieldTestPaginationEntityService } from './field-test-pagination-entity.service';

@Component({
    selector: 'jhi-field-test-pagination-entity-dialog',
    templateUrl: './field-test-pagination-entity-dialog.component.html'
})
export class FieldTestPaginationEntityDialogComponent implements OnInit {

    fieldTestPaginationEntity: FieldTestPaginationEntity;
    authorities: any[];
    isSaving: boolean;
    localDateAliceDp: any;
    localDateRequiredAliceDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestPaginationEntityService: FieldTestPaginationEntityService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, fieldTestPaginationEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestPaginationEntity[field] = base64Data;
                fieldTestPaginationEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestPaginationEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestPaginationEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestPaginationEntityService.update(this.fieldTestPaginationEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestPaginationEntityService.create(this.fieldTestPaginationEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestPaginationEntity>) {
        result.subscribe((res: FieldTestPaginationEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestPaginationEntity) {
        this.eventManager.broadcast({ name: 'fieldTestPaginationEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-pagination-entity-popup',
    template: ''
})
export class FieldTestPaginationEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestPaginationEntityPopupService: FieldTestPaginationEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestPaginationEntityPopupService
                    .open(FieldTestPaginationEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestPaginationEntityPopupService
                    .open(FieldTestPaginationEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
