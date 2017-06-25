import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceImplPaginationAndDTO } from './entity-with-service-impl-pagination-and-dto.model';
import { EntityWithServiceImplPaginationAndDTOPopupService } from './entity-with-service-impl-pagination-and-dto-popup.service';
import { EntityWithServiceImplPaginationAndDTOService } from './entity-with-service-impl-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-impl-pagination-and-dto-delete-dialog',
    templateUrl: './entity-with-service-impl-pagination-and-dto-delete-dialog.component.html'
})
export class EntityWithServiceImplPaginationAndDTODeleteDialogComponent {

    entityWithServiceImplPaginationAndDTO: EntityWithServiceImplPaginationAndDTO;

    constructor(
        private entityWithServiceImplPaginationAndDTOService: EntityWithServiceImplPaginationAndDTOService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceImplPaginationAndDTOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceImplPaginationAndDTOListModification',
                content: 'Deleted an entityWithServiceImplPaginationAndDTO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-impl-pagination-and-dto-delete-popup',
    template: ''
})
export class EntityWithServiceImplPaginationAndDTODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplPaginationAndDTOPopupService: EntityWithServiceImplPaginationAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceImplPaginationAndDTOPopupService
                .open(EntityWithServiceImplPaginationAndDTODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
