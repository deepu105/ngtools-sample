import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceClassPaginationAndDTO } from './entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOService } from './entity-with-service-class-pagination-and-dto.service';

@Injectable()
export class EntityWithServiceClassPaginationAndDTOPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceClassPaginationAndDTOService: EntityWithServiceClassPaginationAndDTOService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceClassPaginationAndDTOService.find(id).subscribe((entityWithServiceClassPaginationAndDTO) => {
                this.entityWithServiceClassPaginationAndDTOModalRef(component, entityWithServiceClassPaginationAndDTO);
            });
        } else {
            return this.entityWithServiceClassPaginationAndDTOModalRef(component, new EntityWithServiceClassPaginationAndDTO());
        }
    }

    entityWithServiceClassPaginationAndDTOModalRef(component: Component,
        entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTO;
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
