import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceImplAndPagination } from './entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationPopupService } from './entity-with-service-impl-and-pagination-popup.service';
import { EntityWithServiceImplAndPaginationService } from './entity-with-service-impl-and-pagination.service';

@Component({
    selector: 'jhi-entity-with-service-impl-and-pagination-delete-dialog',
    templateUrl: './entity-with-service-impl-and-pagination-delete-dialog.component.html'
})
export class EntityWithServiceImplAndPaginationDeleteDialogComponent {

    entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination;

    constructor(
        private entityWithServiceImplAndPaginationService: EntityWithServiceImplAndPaginationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceImplAndPaginationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceImplAndPaginationListModification',
                content: 'Deleted an entityWithServiceImplAndPagination'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-impl-and-pagination-delete-popup',
    template: ''
})
export class EntityWithServiceImplAndPaginationDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplAndPaginationPopupService: EntityWithServiceImplAndPaginationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceImplAndPaginationPopupService
                .open(EntityWithServiceImplAndPaginationDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
