import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceClassAndPagination } from './entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationService } from './entity-with-service-class-and-pagination.service';

@Injectable()
export class EntityWithServiceClassAndPaginationPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceClassAndPaginationService: EntityWithServiceClassAndPaginationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceClassAndPaginationService.find(id).subscribe((entityWithServiceClassAndPagination) => {
                this.entityWithServiceClassAndPaginationModalRef(component, entityWithServiceClassAndPagination);
            });
        } else {
            return this.entityWithServiceClassAndPaginationModalRef(component, new EntityWithServiceClassAndPagination());
        }
    }

    entityWithServiceClassAndPaginationModalRef(component: Component,
        entityWithServiceClassAndPagination: EntityWithServiceClassAndPagination): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceClassAndPagination = entityWithServiceClassAndPagination;
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
