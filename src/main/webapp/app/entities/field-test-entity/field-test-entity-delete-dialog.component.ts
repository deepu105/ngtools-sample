import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestEntity } from './field-test-entity.model';
import { FieldTestEntityPopupService } from './field-test-entity-popup.service';
import { FieldTestEntityService } from './field-test-entity.service';

@Component({
    selector: 'jhi-field-test-entity-delete-dialog',
    templateUrl: './field-test-entity-delete-dialog.component.html'
})
export class FieldTestEntityDeleteDialogComponent {

    fieldTestEntity: FieldTestEntity;

    constructor(
        private fieldTestEntityService: FieldTestEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestEntityListModification',
                content: 'Deleted an fieldTestEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-entity-delete-popup',
    template: ''
})
export class FieldTestEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestEntityPopupService: FieldTestEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestEntityPopupService
                .open(FieldTestEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
