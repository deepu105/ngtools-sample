import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestPaginationEntity } from './field-test-pagination-entity.model';
import { FieldTestPaginationEntityService } from './field-test-pagination-entity.service';

@Injectable()
export class FieldTestPaginationEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestPaginationEntityService: FieldTestPaginationEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestPaginationEntityService.find(id).subscribe((fieldTestPaginationEntity) => {
                if (fieldTestPaginationEntity.localDateAlice) {
                    fieldTestPaginationEntity.localDateAlice = {
                        year: fieldTestPaginationEntity.localDateAlice.getFullYear(),
                        month: fieldTestPaginationEntity.localDateAlice.getMonth() + 1,
                        day: fieldTestPaginationEntity.localDateAlice.getDate()
                    };
                }
                if (fieldTestPaginationEntity.localDateRequiredAlice) {
                    fieldTestPaginationEntity.localDateRequiredAlice = {
                        year: fieldTestPaginationEntity.localDateRequiredAlice.getFullYear(),
                        month: fieldTestPaginationEntity.localDateRequiredAlice.getMonth() + 1,
                        day: fieldTestPaginationEntity.localDateRequiredAlice.getDate()
                    };
                }
                fieldTestPaginationEntity.instantAlice = this.datePipe
                    .transform(fieldTestPaginationEntity.instantAlice, 'yyyy-MM-ddThh:mm');
                fieldTestPaginationEntity.instanteRequiredAlice = this.datePipe
                    .transform(fieldTestPaginationEntity.instanteRequiredAlice, 'yyyy-MM-ddThh:mm');
                fieldTestPaginationEntity.zonedDateTimeAlice = this.datePipe
                    .transform(fieldTestPaginationEntity.zonedDateTimeAlice, 'yyyy-MM-ddThh:mm');
                fieldTestPaginationEntity.zonedDateTimeRequiredAlice = this.datePipe
                    .transform(fieldTestPaginationEntity.zonedDateTimeRequiredAlice, 'yyyy-MM-ddThh:mm');
                this.fieldTestPaginationEntityModalRef(component, fieldTestPaginationEntity);
            });
        } else {
            return this.fieldTestPaginationEntityModalRef(component, new FieldTestPaginationEntity());
        }
    }

    fieldTestPaginationEntityModalRef(component: Component, fieldTestPaginationEntity: FieldTestPaginationEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestPaginationEntity = fieldTestPaginationEntity;
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
