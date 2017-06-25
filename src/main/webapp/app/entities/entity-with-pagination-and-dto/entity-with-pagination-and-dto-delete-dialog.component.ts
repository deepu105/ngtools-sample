import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithPaginationAndDTO } from './entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOPopupService } from './entity-with-pagination-and-dto-popup.service';
import { EntityWithPaginationAndDTOService } from './entity-with-pagination-and-dto.service';

@Component({
    selector: 'jhi-entity-with-pagination-and-dto-delete-dialog',
    templateUrl: './entity-with-pagination-and-dto-delete-dialog.component.html'
})
export class EntityWithPaginationAndDTODeleteDialogComponent {

    entityWithPaginationAndDTO: EntityWithPaginationAndDTO;

    constructor(
        private entityWithPaginationAndDTOService: EntityWithPaginationAndDTOService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithPaginationAndDTOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithPaginationAndDTOListModification',
                content: 'Deleted an entityWithPaginationAndDTO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-pagination-and-dto-delete-popup',
    template: ''
})
export class EntityWithPaginationAndDTODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithPaginationAndDTOPopupService: EntityWithPaginationAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithPaginationAndDTOPopupService
                .open(EntityWithPaginationAndDTODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
