import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestServiceImplEntity } from './field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityPopupService } from './field-test-service-impl-entity-popup.service';
import { FieldTestServiceImplEntityService } from './field-test-service-impl-entity.service';

@Component({
    selector: 'jhi-field-test-service-impl-entity-dialog',
    templateUrl: './field-test-service-impl-entity-dialog.component.html'
})
export class FieldTestServiceImplEntityDialogComponent implements OnInit {

    fieldTestServiceImplEntity: FieldTestServiceImplEntity;
    authorities: any[];
    isSaving: boolean;
    localDateMikaDp: any;
    localDateRequiredMikaDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestServiceImplEntityService: FieldTestServiceImplEntityService,
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

    setFileData(event, fieldTestServiceImplEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestServiceImplEntity[field] = base64Data;
                fieldTestServiceImplEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestServiceImplEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestServiceImplEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestServiceImplEntityService.update(this.fieldTestServiceImplEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestServiceImplEntityService.create(this.fieldTestServiceImplEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestServiceImplEntity>) {
        result.subscribe((res: FieldTestServiceImplEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestServiceImplEntity) {
        this.eventManager.broadcast({ name: 'fieldTestServiceImplEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-service-impl-entity-popup',
    template: ''
})
export class FieldTestServiceImplEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestServiceImplEntityPopupService: FieldTestServiceImplEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestServiceImplEntityPopupService
                    .open(FieldTestServiceImplEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestServiceImplEntityPopupService
                    .open(FieldTestServiceImplEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
