import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestMapstructEntity } from './field-test-mapstruct-entity.model';
import { FieldTestMapstructEntityPopupService } from './field-test-mapstruct-entity-popup.service';
import { FieldTestMapstructEntityService } from './field-test-mapstruct-entity.service';

@Component({
    selector: 'jhi-field-test-mapstruct-entity-dialog',
    templateUrl: './field-test-mapstruct-entity-dialog.component.html'
})
export class FieldTestMapstructEntityDialogComponent implements OnInit {

    fieldTestMapstructEntity: FieldTestMapstructEntity;
    authorities: any[];
    isSaving: boolean;
    localDateEvaDp: any;
    localDateRequiredEvaDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestMapstructEntityService: FieldTestMapstructEntityService,
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

    setFileData(event, fieldTestMapstructEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestMapstructEntity[field] = base64Data;
                fieldTestMapstructEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestMapstructEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestMapstructEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestMapstructEntityService.update(this.fieldTestMapstructEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestMapstructEntityService.create(this.fieldTestMapstructEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestMapstructEntity>) {
        result.subscribe((res: FieldTestMapstructEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestMapstructEntity) {
        this.eventManager.broadcast({ name: 'fieldTestMapstructEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-mapstruct-entity-popup',
    template: ''
})
export class FieldTestMapstructEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestMapstructEntityPopupService: FieldTestMapstructEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestMapstructEntityPopupService
                    .open(FieldTestMapstructEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestMapstructEntityPopupService
                    .open(FieldTestMapstructEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
