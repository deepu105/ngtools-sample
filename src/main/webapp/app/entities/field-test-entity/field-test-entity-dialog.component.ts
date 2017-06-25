import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestEntity } from './field-test-entity.model';
import { FieldTestEntityPopupService } from './field-test-entity-popup.service';
import { FieldTestEntityService } from './field-test-entity.service';

@Component({
    selector: 'jhi-field-test-entity-dialog',
    templateUrl: './field-test-entity-dialog.component.html'
})
export class FieldTestEntityDialogComponent implements OnInit {

    fieldTestEntity: FieldTestEntity;
    authorities: any[];
    isSaving: boolean;
    localDateTomDp: any;
    localDateRequiredTomDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestEntityService: FieldTestEntityService,
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

    setFileData(event, fieldTestEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestEntity[field] = base64Data;
                fieldTestEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestEntityService.update(this.fieldTestEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestEntityService.create(this.fieldTestEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestEntity>) {
        result.subscribe((res: FieldTestEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestEntity) {
        this.eventManager.broadcast({ name: 'fieldTestEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-entity-popup',
    template: ''
})
export class FieldTestEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestEntityPopupService: FieldTestEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestEntityPopupService
                    .open(FieldTestEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestEntityPopupService
                    .open(FieldTestEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
