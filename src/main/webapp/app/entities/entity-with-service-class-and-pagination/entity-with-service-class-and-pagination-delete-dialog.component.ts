import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceClassAndPagination } from './entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationPopupService } from './entity-with-service-class-and-pagination-popup.service';
import { EntityWithServiceClassAndPaginationService } from './entity-with-service-class-and-pagination.service';

@Component({
    selector: 'jhi-entity-with-service-class-and-pagination-delete-dialog',
    templateUrl: './entity-with-service-class-and-pagination-delete-dialog.component.html'
})
export class EntityWithServiceClassAndPaginationDeleteDialogComponent {

    entityWithServiceClassAndPagination: EntityWithServiceClassAndPagination;

    constructor(
        private entityWithServiceClassAndPaginationService: EntityWithServiceClassAndPaginationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceClassAndPaginationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceClassAndPaginationListModification',
                content: 'Deleted an entityWithServiceClassAndPagination'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-class-and-pagination-delete-popup',
    template: ''
})
export class EntityWithServiceClassAndPaginationDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassAndPaginationPopupService: EntityWithServiceClassAndPaginationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceClassAndPaginationPopupService
                .open(EntityWithServiceClassAndPaginationDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
