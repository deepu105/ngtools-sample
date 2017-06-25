import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestPagerEntity } from './field-test-pager-entity.model';
import { FieldTestPagerEntityPopupService } from './field-test-pager-entity-popup.service';
import { FieldTestPagerEntityService } from './field-test-pager-entity.service';

@Component({
    selector: 'jhi-field-test-pager-entity-delete-dialog',
    templateUrl: './field-test-pager-entity-delete-dialog.component.html'
})
export class FieldTestPagerEntityDeleteDialogComponent {

    fieldTestPagerEntity: FieldTestPagerEntity;

    constructor(
        private fieldTestPagerEntityService: FieldTestPagerEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestPagerEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestPagerEntityListModification',
                content: 'Deleted an fieldTestPagerEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-pager-entity-delete-popup',
    template: ''
})
export class FieldTestPagerEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestPagerEntityPopupService: FieldTestPagerEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestPagerEntityPopupService
                .open(FieldTestPagerEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
