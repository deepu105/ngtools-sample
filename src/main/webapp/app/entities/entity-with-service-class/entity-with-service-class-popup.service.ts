import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceClass } from './entity-with-service-class.model';
import { EntityWithServiceClassService } from './entity-with-service-class.service';

@Injectable()
export class EntityWithServiceClassPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceClassService: EntityWithServiceClassService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceClassService.find(id).subscribe((entityWithServiceClass) => {
                this.entityWithServiceClassModalRef(component, entityWithServiceClass);
            });
        } else {
            return this.entityWithServiceClassModalRef(component, new EntityWithServiceClass());
        }
    }

    entityWithServiceClassModalRef(component: Component, entityWithServiceClass: EntityWithServiceClass): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceClass = entityWithServiceClass;
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
