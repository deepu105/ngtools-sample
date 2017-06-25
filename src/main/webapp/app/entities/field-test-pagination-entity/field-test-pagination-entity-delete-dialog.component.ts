import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestPaginationEntity } from './field-test-pagination-entity.model';
import { FieldTestPaginationEntityPopupService } from './field-test-pagination-entity-popup.service';
import { FieldTestPaginationEntityService } from './field-test-pagination-entity.service';

@Component({
    selector: 'jhi-field-test-pagination-entity-delete-dialog',
    templateUrl: './field-test-pagination-entity-delete-dialog.component.html'
})
export class FieldTestPaginationEntityDeleteDialogComponent {

    fieldTestPaginationEntity: FieldTestPaginationEntity;

    constructor(
        private fieldTestPaginationEntityService: FieldTestPaginationEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestPaginationEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestPaginationEntityListModification',
                content: 'Deleted an fieldTestPaginationEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-pagination-entity-delete-popup',
    template: ''
})
export class FieldTestPaginationEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestPaginationEntityPopupService: FieldTestPaginationEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestPaginationEntityPopupService
                .open(FieldTestPaginationEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
