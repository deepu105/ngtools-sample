import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FieldTestInfiniteScrollEntity } from './field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityPopupService } from './field-test-infinite-scroll-entity-popup.service';
import { FieldTestInfiniteScrollEntityService } from './field-test-infinite-scroll-entity.service';

@Component({
    selector: 'jhi-field-test-infinite-scroll-entity-delete-dialog',
    templateUrl: './field-test-infinite-scroll-entity-delete-dialog.component.html'
})
export class FieldTestInfiniteScrollEntityDeleteDialogComponent {

    fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity;

    constructor(
        private fieldTestInfiniteScrollEntityService: FieldTestInfiniteScrollEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldTestInfiniteScrollEntityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fieldTestInfiniteScrollEntityListModification',
                content: 'Deleted an fieldTestInfiniteScrollEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-field-test-infinite-scroll-entity-delete-popup',
    template: ''
})
export class FieldTestInfiniteScrollEntityDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fieldTestInfiniteScrollEntityPopupService: FieldTestInfiniteScrollEntityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.fieldTestInfiniteScrollEntityPopupService
                .open(FieldTestInfiniteScrollEntityDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
