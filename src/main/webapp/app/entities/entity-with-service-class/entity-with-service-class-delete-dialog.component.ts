import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceClass } from './entity-with-service-class.model';
import { EntityWithServiceClassPopupService } from './entity-with-service-class-popup.service';
import { EntityWithServiceClassService } from './entity-with-service-class.service';

@Component({
    selector: 'jhi-entity-with-service-class-delete-dialog',
    templateUrl: './entity-with-service-class-delete-dialog.component.html'
})
export class EntityWithServiceClassDeleteDialogComponent {

    entityWithServiceClass: EntityWithServiceClass;

    constructor(
        private entityWithServiceClassService: EntityWithServiceClassService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceClassService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceClassListModification',
                content: 'Deleted an entityWithServiceClass'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-class-delete-popup',
    template: ''
})
export class EntityWithServiceClassDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceClassPopupService: EntityWithServiceClassPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceClassPopupService
                .open(EntityWithServiceClassDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
