import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestMapstructEntity } from './field-test-mapstruct-entity.model';
import { FieldTestMapstructEntityService } from './field-test-mapstruct-entity.service';

@Injectable()
export class FieldTestMapstructEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestMapstructEntityService: FieldTestMapstructEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestMapstructEntityService.find(id).subscribe((fieldTestMapstructEntity) => {
                if (fieldTestMapstructEntity.localDateEva) {
                    fieldTestMapstructEntity.localDateEva = {
                        year: fieldTestMapstructEntity.localDateEva.getFullYear(),
                        month: fieldTestMapstructEntity.localDateEva.getMonth() + 1,
                        day: fieldTestMapstructEntity.localDateEva.getDate()
                    };
                }
                if (fieldTestMapstructEntity.localDateRequiredEva) {
                    fieldTestMapstructEntity.localDateRequiredEva = {
                        year: fieldTestMapstructEntity.localDateRequiredEva.getFullYear(),
                        month: fieldTestMapstructEntity.localDateRequiredEva.getMonth() + 1,
                        day: fieldTestMapstructEntity.localDateRequiredEva.getDate()
                    };
                }
                fieldTestMapstructEntity.instantEva = this.datePipe
                    .transform(fieldTestMapstructEntity.instantEva, 'yyyy-MM-ddThh:mm');
                fieldTestMapstructEntity.instanteRequiredEva = this.datePipe
                    .transform(fieldTestMapstructEntity.instanteRequiredEva, 'yyyy-MM-ddThh:mm');
                fieldTestMapstructEntity.zonedDateTimeEva = this.datePipe
                    .transform(fieldTestMapstructEntity.zonedDateTimeEva, 'yyyy-MM-ddThh:mm');
                fieldTestMapstructEntity.zonedDateTimeRequiredEva = this.datePipe
                    .transform(fieldTestMapstructEntity.zonedDateTimeRequiredEva, 'yyyy-MM-ddThh:mm');
                this.fieldTestMapstructEntityModalRef(component, fieldTestMapstructEntity);
            });
        } else {
            return this.fieldTestMapstructEntityModalRef(component, new FieldTestMapstructEntity());
        }
    }

    fieldTestMapstructEntityModalRef(component: Component, fieldTestMapstructEntity: FieldTestMapstructEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestMapstructEntity = fieldTestMapstructEntity;
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
