import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithPaginationAndDTO } from './entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOService } from './entity-with-pagination-and-dto.service';

@Injectable()
export class EntityWithPaginationAndDTOPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithPaginationAndDTOService: EntityWithPaginationAndDTOService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithPaginationAndDTOService.find(id).subscribe((entityWithPaginationAndDTO) => {
                this.entityWithPaginationAndDTOModalRef(component, entityWithPaginationAndDTO);
            });
        } else {
            return this.entityWithPaginationAndDTOModalRef(component, new EntityWithPaginationAndDTO());
        }
    }

    entityWithPaginationAndDTOModalRef(component: Component, entityWithPaginationAndDTO: EntityWithPaginationAndDTO): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithPaginationAndDTO = entityWithPaginationAndDTO;
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
