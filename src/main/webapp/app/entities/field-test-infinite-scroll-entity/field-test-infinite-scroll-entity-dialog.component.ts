import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FieldTestInfiniteScrollEntity } from './field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityPopupService } from './field-test-infinite-scroll-entity-popup.service';
import { FieldTestInfiniteScrollEntityService } from './field-test-infinite-scroll-entity.service';

@Component({
    selector: 'jhi-field-test-infinite-scroll-entity-dialog',
    templateUrl: './field-test-infinite-scroll-entity-dialog.component.html'
})
export class FieldTestInfiniteScrollEntityDialogComponent implements OnInit {

    fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity;
    authorities: any[];
    isSaving: boolean;
    localDateHugoDp: any;
    localDateRequiredHugoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private fieldTestInfiniteScrollEntityService: FieldTestInfiniteScrollEntityService,
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

    setFileData(event, fieldTestInfiniteScrollEntity, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                fieldTestInfiniteScrollEntity[field] = base64Data;
                fieldTestInfiniteScrollEntity[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fieldTestInfiniteScrollEntity, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fieldTestInfiniteScrollEntity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fieldTestInfiniteScrollEntityService.update(this.fieldTestInfiniteScrollEntity));
        } else {
            this.subscribeToSaveResponse(
                this.fieldTestInfiniteScrollEntityService.create(this.fieldTestInfiniteScrollEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<FieldTestInfiniteScrollEntity>) {
        result.subscribe((res: FieldTestInfiniteScrollEntity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: FieldTestInfiniteScrollEntity) {
        this.eventManager.broadcast({ name: 'fieldTestInfiniteScrollEntityListModification', content: 'OK'});
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
    selector: 'jhi-field-test-infinite-scroll-entity-popup',
    template: ''
})
export class FieldTestInfiniteScrollEntityPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestInfiniteScrollEntityPopupService: FieldTestInfiniteScrollEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.fieldTestInfiniteScrollEntityPopupService
                    .open(FieldTestInfiniteScrollEntityDialogComponent, params['id']);
            } else {
                this.modalRef = this.fieldTestInfiniteScrollEntityPopupService
                    .open(FieldTestInfiniteScrollEntityDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
