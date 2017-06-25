import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceImplAndDTO } from './entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOPopupService } from './entity-with-service-impl-and-dto-popup.service';
import { EntityWithServiceImplAndDTOService } from './entity-with-service-impl-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-impl-and-dto-delete-dialog',
    templateUrl: './entity-with-service-impl-and-dto-delete-dialog.component.html'
})
export class EntityWithServiceImplAndDTODeleteDialogComponent {

    entityWithServiceImplAndDTO: EntityWithServiceImplAndDTO;

    constructor(
        private entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceImplAndDTOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceImplAndDTOListModification',
                content: 'Deleted an entityWithServiceImplAndDTO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-impl-and-dto-delete-popup',
    template: ''
})
export class EntityWithServiceImplAndDTODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplAndDTOPopupService: EntityWithServiceImplAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceImplAndDTOPopupService
                .open(EntityWithServiceImplAndDTODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
