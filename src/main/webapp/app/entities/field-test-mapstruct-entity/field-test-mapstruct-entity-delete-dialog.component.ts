import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestMapstructEntity } from './field-test-mapstruct-entity.model';
import { FieldTestMapstructEntityPopupService } from './field-test-mapstruct-entity-popup.service';
import { FieldTestMapstructEntityService } from './field-test-mapstruct-entity.service';

@Component({
    selector: 'jhi-field-test-mapstruct-entity-delete-dialog',
    templateUrl: './field-test-mapstruct-entity-delete-dialog.component.html'
})
export class FieldTestMapstructEntityDeleteDialogComponent {

    fieldTestMapstructEntity: FieldTestMapstructEntity;

    constructor(
        private fieldTestMapstructEntityService: FieldTestMapstructEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestMapstructEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestMapstructEntityListModification',
                content: 'Deleted an fieldTestMapstructEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-mapstruct-entity-delete-popup',
    template: ''
})
export class FieldTestMapstructEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestMapstructEntityPopupService: FieldTestMapstructEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestMapstructEntityPopupService
                .open(FieldTestMapstructEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
