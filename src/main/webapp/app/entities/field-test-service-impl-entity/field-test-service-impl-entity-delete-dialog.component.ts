import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestServiceImplEntity } from './field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityPopupService } from './field-test-service-impl-entity-popup.service';
import { FieldTestServiceImplEntityService } from './field-test-service-impl-entity.service';

@Component({
    selector: 'jhi-field-test-service-impl-entity-delete-dialog',
    templateUrl: './field-test-service-impl-entity-delete-dialog.component.html'
})
export class FieldTestServiceImplEntityDeleteDialogComponent {

    fieldTestServiceImplEntity: FieldTestServiceImplEntity;

    constructor(
        private fieldTestServiceImplEntityService: FieldTestServiceImplEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestServiceImplEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestServiceImplEntityListModification',
                content: 'Deleted an fieldTestServiceImplEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-service-impl-entity-delete-popup',
    template: ''
})
export class FieldTestServiceImplEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestServiceImplEntityPopupService: FieldTestServiceImplEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestServiceImplEntityPopupService
                .open(FieldTestServiceImplEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
