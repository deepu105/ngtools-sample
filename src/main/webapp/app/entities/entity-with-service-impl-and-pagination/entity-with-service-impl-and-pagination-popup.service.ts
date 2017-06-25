import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceImplAndPagination } from './entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationService } from './entity-with-service-impl-and-pagination.service';

@Injectable()
export class EntityWithServiceImplAndPaginationPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceImplAndPaginationService: EntityWithServiceImplAndPaginationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceImplAndPaginationService.find(id).subscribe((entityWithServiceImplAndPagination) => {
                this.entityWithServiceImplAndPaginationModalRef(component, entityWithServiceImplAndPagination);
            });
        } else {
            return this.entityWithServiceImplAndPaginationModalRef(component, new EntityWithServiceImplAndPagination());
        }
    }

    entityWithServiceImplAndPaginationModalRef(component: Component,
        entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceImplAndPagination = entityWithServiceImplAndPagination;
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
