import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntityWithServiceImpl } from './entity-with-service-impl.model';
import { EntityWithServiceImplPopupService } from './entity-with-service-impl-popup.service';
import { EntityWithServiceImplService } from './entity-with-service-impl.service';

@Component({
    selector: 'jhi-entity-with-service-impl-delete-dialog',
    templateUrl: './entity-with-service-impl-delete-dialog.component.html'
})
export class EntityWithServiceImplDeleteDialogComponent {

    entityWithServiceImpl: EntityWithServiceImpl;

    constructor(
        private entityWithServiceImplService: EntityWithServiceImplService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityWithServiceImplService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entityWithServiceImplListModification',
                content: 'Deleted an entityWithServiceImpl'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-with-service-impl-delete-popup',
    template: ''
})
export class EntityWithServiceImplDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entityWithServiceImplPopupService: EntityWithServiceImplPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entityWithServiceImplPopupService
                .open(EntityWithServiceImplDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
