import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithDTO } from './entity-with-dto.model';
import { EntityWithDTOPopupService } from './entity-with-dto-popup.service';
import { EntityWithDTOService } from './entity-with-dto.service';

@Component({
    selector: 'jhi-entity-with-dto-delete-dialog',
    templateUrl: './entity-with-dto-delete-dialog.component.html'
})
export class EntityWithDTODeleteDialogComponent {

    entityWithDTO: EntityWithDTO;

    constructor(
        private entityWithDTOService: EntityWithDTOService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithDTOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithDTOListModification',
                content: 'Deleted an entityWithDTO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-dto-delete-popup',
    template: ''
})
export class EntityWithDTODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithDTOPopupService: EntityWithDTOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithDTOPopupService
                .open(EntityWithDTODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
