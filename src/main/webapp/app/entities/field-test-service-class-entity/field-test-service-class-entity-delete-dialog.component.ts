import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestServiceClassEntity } from './field-test-service-class-entity.model';
import { FieldTestServiceClassEntityPopupService } from './field-test-service-class-entity-popup.service';
import { FieldTestServiceClassEntityService } from './field-test-service-class-entity.service';

@Component({
    selector: 'jhi-field-test-service-class-entity-delete-dialog',
    templateUrl: './field-test-service-class-entity-delete-dialog.component.html'
})
export class FieldTestServiceClassEntityDeleteDialogComponent {

    fieldTestServiceClassEntity: FieldTestServiceClassEntity;

    constructor(
        private fieldTestServiceClassEntityService: FieldTestServiceClassEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestServiceClassEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestServiceClassEntityListModification',
                content: 'Deleted an fieldTestServiceClassEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-service-class-entity-delete-popup',
    template: ''
})
export class FieldTestServiceClassEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestServiceClassEntityPopupService: FieldTestServiceClassEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestServiceClassEntityPopupService
                .open(FieldTestServiceClassEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
