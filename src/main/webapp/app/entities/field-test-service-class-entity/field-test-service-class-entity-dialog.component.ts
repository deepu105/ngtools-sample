import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestServiceClassEntity } from './field-test-service-class-entity.model';
import { FieldTestServiceClassEntityPopupService } from './field-test-service-class-entity-popup.service';
import { FieldTestServiceClassEntityService } from './field-test-service-class-entity.service';

@Component({
    selector: 'jhi-field-test-service-class-entity-dialog',
    templateUrl: './field-test-service-class-entity-dialog.component.html'
})
export class FieldTestServiceClassEntityDialogComponent implements OnInit {

    fieldTestServiceClassEntity: FieldTestServiceClassEntity;
    authorities: any[];
    isSaving: boolean;
    localDateBobDp: any;
    localDateRequiredBobDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestServiceClassEntityService: FieldTestServiceClassEntityService,
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

    setFileData(event, fieldTestServiceClassEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestServiceClassEntity[field] = base64Data;
                fieldTestServiceClassEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestServiceClassEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestServiceClassEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestServiceClassEntityService.update(this.fieldTestServiceClassEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestServiceClassEntityService.create(this.fieldTestServiceClassEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestServiceClassEntity>) {
        result.subscribe((res: FieldTestServiceClassEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestServiceClassEntity) {
        this.eventManager.broadcast({ name: 'fieldTestServiceClassEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-service-class-entity-popup',
    template: ''
})
export class FieldTestServiceClassEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestServiceClassEntityPopupService: FieldTestServiceClassEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestServiceClassEntityPopupService
                    .open(FieldTestServiceClassEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestServiceClassEntityPopupService
                    .open(FieldTestServiceClassEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
