import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithPagination } from './entity-with-pagination.model';
import { EntityWithPaginationService } from './entity-with-pagination.service';

@Injectable()
export class EntityWithPaginationPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithPaginationService: EntityWithPaginationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithPaginationService.find(id).subscribe((entityWithPagination) => {
                this.entityWithPaginationModalRef(component, entityWithPagination);
            });
        } else {
            return this.entityWithPaginationModalRef(component, new EntityWithPagination());
        }
    }

    entityWithPaginationModalRef(component: Component, entityWithPagination: EntityWithPagination): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithPagination = entityWithPagination;
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
