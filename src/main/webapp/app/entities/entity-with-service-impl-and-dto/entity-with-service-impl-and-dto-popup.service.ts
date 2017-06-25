import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceImplAndDTO } from './entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from './entity-with-service-impl-and-dto.service';

@Injectable()
export class EntityWithServiceImplAndDTOPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceImplAndDTOService.find(id).subscribe((entityWithServiceImplAndDTO) => {
                this.entityWithServiceImplAndDTOModalRef(component, entityWithServiceImplAndDTO);
            });
        } else {
            return this.entityWithServiceImplAndDTOModalRef(component, new EntityWithServiceImplAndDTO());
        }
    }

    entityWithServiceImplAndDTOModalRef(component: Component, entityWithServiceImplAndDTO: EntityWithServiceImplAndDTO): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceImplAndDTO = entityWithServiceImplAndDTO;
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
