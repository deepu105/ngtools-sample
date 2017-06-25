import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceClassPaginationAndDTO } from './entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOPopupService } from './entity-with-service-class-pagination-and-dto-popup.service';
import { EntityWithServiceClassPaginationAndDTOService } from './entity-with-service-class-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-class-pagination-and-dto-delete-dialog',
    templateUrl: './entity-with-service-class-pagination-and-dto-delete-dialog.component.html'
})
export class EntityWithServiceClassPaginationAndDTODeleteDialogComponent {

    entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO;

    constructor(
        private entityWithServiceClassPaginationAndDTOService: EntityWithServiceClassPaginationAndDTOService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceClassPaginationAndDTOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceClassPaginationAndDTOListModification',
                content: 'Deleted an entityWithServiceClassPaginationAndDTO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-class-pagination-and-dto-delete-popup',
    template: ''
})
export class EntityWithServiceClassPaginationAndDTODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassPaginationAndDTOPopupService: EntityWithServiceClassPaginationAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceClassPaginationAndDTOPopupService
                .open(EntityWithServiceClassPaginationAndDTODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
