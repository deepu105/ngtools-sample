import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithPagination } from './entity-with-pagination.model';
import { EntityWithPaginationPopupService } from './entity-with-pagination-popup.service';
import { EntityWithPaginationService } from './entity-with-pagination.service';

@Component({
    selector: 'jhi-entity-with-pagination-delete-dialog',
    templateUrl: './entity-with-pagination-delete-dialog.component.html'
})
export class EntityWithPaginationDeleteDialogComponent {

    entityWithPagination: EntityWithPagination;

    constructor(
        private entityWithPaginationService: EntityWithPaginationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithPaginationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithPaginationListModification',
                content: 'Deleted an entityWithPagination'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-pagination-delete-popup',
    template: ''
})
export class EntityWithPaginationDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithPaginationPopupService: EntityWithPaginationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithPaginationPopupService
                .open(EntityWithPaginationDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
