import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestServiceImplEntity } from './field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from './field-test-service-impl-entity.service';

@Injectable()
export class FieldTestServiceImplEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestServiceImplEntityService: FieldTestServiceImplEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestServiceImplEntityService.find(id).subscribe((fieldTestServiceImplEntity) => {
                if (fieldTestServiceImplEntity.localDateMika) {
                    fieldTestServiceImplEntity.localDateMika = {
                        year: fieldTestServiceImplEntity.localDateMika.getFullYear(),
                        month: fieldTestServiceImplEntity.localDateMika.getMonth() + 1,
                        day: fieldTestServiceImplEntity.localDateMika.getDate()
                    };
                }
                if (fieldTestServiceImplEntity.localDateRequiredMika) {
                    fieldTestServiceImplEntity.localDateRequiredMika = {
                        year: fieldTestServiceImplEntity.localDateRequiredMika.getFullYear(),
                        month: fieldTestServiceImplEntity.localDateRequiredMika.getMonth() + 1,
                        day: fieldTestServiceImplEntity.localDateRequiredMika.getDate()
                    };
                }
                fieldTestServiceImplEntity.instantMika = this.datePipe
                    .transform(fieldTestServiceImplEntity.instantMika, 'yyyy-MM-ddThh:mm');
                fieldTestServiceImplEntity.instanteRequiredMika = this.datePipe
                    .transform(fieldTestServiceImplEntity.instanteRequiredMika, 'yyyy-MM-ddThh:mm');
                fieldTestServiceImplEntity.zonedDateTimeMika = this.datePipe
                    .transform(fieldTestServiceImplEntity.zonedDateTimeMika, 'yyyy-MM-ddThh:mm');
                fieldTestServiceImplEntity.zonedDateTimeRequiredMika = this.datePipe
                    .transform(fieldTestServiceImplEntity.zonedDateTimeRequiredMika, 'yyyy-MM-ddThh:mm');
                this.fieldTestServiceImplEntityModalRef(component, fieldTestServiceImplEntity);
            });
        } else {
            return this.fieldTestServiceImplEntityModalRef(component, new FieldTestServiceImplEntity());
        }
    }

    fieldTestServiceImplEntityModalRef(component: Component, fieldTestServiceImplEntity: FieldTestServiceImplEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestServiceImplEntity = fieldTestServiceImplEntity;
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
