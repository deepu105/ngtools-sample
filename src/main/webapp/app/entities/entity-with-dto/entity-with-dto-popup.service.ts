import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntityWithDTO } from './entity-with-dto.model';
import { EntityWithDTOService } from './entity-with-dto.service';

@Injectable()
export class EntityWithDTOPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entityWithDTOService: EntityWithDTOService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entityWithDTOService.find(id).subscribe((entityWithDTO) => {
                this.entityWithDTOModalRef(component, entityWithDTO);
            });
        } else {
            return this.entityWithDTOModalRef(component, new EntityWithDTO());
        }
    }

    entityWithDTOModalRef(component: Component, entityWithDTO: EntityWithDTO): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entityWithDTO = entityWithDTO;
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
