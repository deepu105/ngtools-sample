import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceClassAndDTO } from './entity-with-service-class-and-dto.model';
import { EntityWithServiceClassAndDTOPopupService } from './entity-with-service-class-and-dto-popup.service';
import { EntityWithServiceClassAndDTOService } from './entity-with-service-class-and-dto.service';

@Component({
    selector: 'jhi-entity-with-service-class-and-dto-delete-dialog',
    templateUrl: './entity-with-service-class-and-dto-delete-dialog.component.html'
})
export class EntityWithServiceClassAndDTODeleteDialogComponent {

    entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO;

    constructor(
        private entityWithServiceClassAndDTOService: EntityWithServiceClassAndDTOService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceClassAndDTOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceClassAndDTOListModification',
                content: 'Deleted an entityWithServiceClassAndDTO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-class-and-dto-delete-popup',
    template: ''
})
export class EntityWithServiceClassAndDTODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassAndDTOPopupService: EntityWithServiceClassAndDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceClassAndDTOPopupService
                .open(EntityWithServiceClassAndDTODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
