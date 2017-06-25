import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceImpl } from './entity-with-service-impl.model';
import { EntityWithServiceImplService } from './entity-with-service-impl.service';

@Injectable()
export class EntityWithServiceImplPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceImplService: EntityWithServiceImplService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceImplService.find(id).subscribe((entityWithServiceImpl) => {
                this.entityWithServiceImplModalRef(component, entityWithServiceImpl);
            });
        } else {
            return this.entityWithServiceImplModalRef(component, new EntityWithServiceImpl());
        }
    }

    entityWithServiceImplModalRef(component: Component, entityWithServiceImpl: EntityWithServiceImpl): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceImpl = entityWithServiceImpl;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
