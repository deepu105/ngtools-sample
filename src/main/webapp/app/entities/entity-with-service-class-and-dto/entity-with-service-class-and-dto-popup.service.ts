import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithServiceClassAndDTO } from './entity-with-service-class-and-dto.model';
import { EntityWithServiceClassAndDTOService } from './entity-with-service-class-and-dto.service';

@Injectable()
export class EntityWithServiceClassAndDTOPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithServiceClassAndDTOService: EntityWithServiceClassAndDTOService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithServiceClassAndDTOService.find(id).subscribe((entityWithServiceClassAndDTO) => {
                this.entityWithServiceClassAndDTOModalRef(component, entityWithServiceClassAndDTO);
            });
        } else {
            return this.entityWithServiceClassAndDTOModalRef(component, new EntityWithServiceClassAndDTO());
        }
    }

    entityWithServiceClassAndDTOModalRef(component: Component, entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithServiceClassAndDTO = entityWithServiceClassAndDTO;
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
