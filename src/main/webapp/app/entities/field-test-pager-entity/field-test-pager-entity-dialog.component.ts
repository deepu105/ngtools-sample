import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestPagerEntity } from './field-test-pager-entity.model';
import { FieldTestPagerEntityPopupService } from './field-test-pager-entity-popup.service';
import { FieldTestPagerEntityService } from './field-test-pager-entity.service';

@Component({
    selector: 'jhi-field-test-pager-entity-dialog',
    templateUrl: './field-test-pager-entity-dialog.component.html'
})
export class FieldTestPagerEntityDialogComponent implements OnInit {

    fieldTestPagerEntity: FieldTestPagerEntity;
    authorities: any[];
    isSaving: boolean;
    localDateJadeDp: any;
    localDateRequiredJadeDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestPagerEntityService: FieldTestPagerEntityService,
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

    setFileData(event, fieldTestPagerEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestPagerEntity[field] = base64Data;
                fieldTestPagerEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestPagerEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestPagerEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestPagerEntityService.update(this.fieldTestPagerEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestPagerEntityService.create(this.fieldTestPagerEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestPagerEntity>) {
        result.subscribe((res: FieldTestPagerEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestPagerEntity) {
        this.eventManager.broadcast({ name: 'fieldTestPagerEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-pager-entity-popup',
    template: ''
})
export class FieldTestPagerEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestPagerEntityPopupService: FieldTestPagerEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestPagerEntityPopupService
                    .open(FieldTestPagerEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestPagerEntityPopupService
                    .open(FieldTestPagerEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
