import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestEntity } from './field-test-entity.model';
import { FieldTestEntityService } from './field-test-entity.service';

@Injectable()
export class FieldTestEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestEntityService: FieldTestEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestEntityService.find(id).subscribe((fieldTestEntity) => {
                if (fieldTestEntity.localDateTom) {
                    fieldTestEntity.localDateTom = {
                        year: fieldTestEntity.localDateTom.getFullYear(),
                        month: fieldTestEntity.localDateTom.getMonth() + 1,
                        day: fieldTestEntity.localDateTom.getDate()
                    };
                }
                if (fieldTestEntity.localDateRequiredTom) {
                    fieldTestEntity.localDateRequiredTom = {
                        year: fieldTestEntity.localDateRequiredTom.getFullYear(),
                        month: fieldTestEntity.localDateRequiredTom.getMonth() + 1,
                        day: fieldTestEntity.localDateRequiredTom.getDate()
                    };
                }
                fieldTestEntity.instantTom = this.datePipe
                    .transform(fieldTestEntity.instantTom, 'yyyy-MM-ddThh:mm');
                fieldTestEntity.instanteRequiredTom = this.datePipe
                    .transform(fieldTestEntity.instanteRequiredTom, 'yyyy-MM-ddThh:mm');
                fieldTestEntity.zonedDateTimeTom = this.datePipe
                    .transform(fieldTestEntity.zonedDateTimeTom, 'yyyy-MM-ddThh:mm');
                fieldTestEntity.zonedDateTimeRequiredTom = this.datePipe
                    .transform(fieldTestEntity.zonedDateTimeRequiredTom, 'yyyy-MM-ddThh:mm');
                this.fieldTestEntityModalRef(component, fieldTestEntity);
            });
        } else {
            return this.fieldTestEntityModalRef(component, new FieldTestEntity());
        }
    }

    fieldTestEntityModalRef(component: Component, fieldTestEntity: FieldTestEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestEntity = fieldTestEntity;
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
