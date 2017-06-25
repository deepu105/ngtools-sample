import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestPagerEntity } from './field-test-pager-entity.model';
import { FieldTestPagerEntityService } from './field-test-pager-entity.service';

@Injectable()
export class FieldTestPagerEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestPagerEntityService: FieldTestPagerEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestPagerEntityService.find(id).subscribe((fieldTestPagerEntity) => {
                if (fieldTestPagerEntity.localDateJade) {
                    fieldTestPagerEntity.localDateJade = {
                        year: fieldTestPagerEntity.localDateJade.getFullYear(),
                        month: fieldTestPagerEntity.localDateJade.getMonth() + 1,
                        day: fieldTestPagerEntity.localDateJade.getDate()
                    };
                }
                if (fieldTestPagerEntity.localDateRequiredJade) {
                    fieldTestPagerEntity.localDateRequiredJade = {
                        year: fieldTestPagerEntity.localDateRequiredJade.getFullYear(),
                        month: fieldTestPagerEntity.localDateRequiredJade.getMonth() + 1,
                        day: fieldTestPagerEntity.localDateRequiredJade.getDate()
                    };
                }
                fieldTestPagerEntity.instantJade = this.datePipe
                    .transform(fieldTestPagerEntity.instantJade, 'yyyy-MM-ddThh:mm');
                fieldTestPagerEntity.instanteRequiredJade = this.datePipe
                    .transform(fieldTestPagerEntity.instanteRequiredJade, 'yyyy-MM-ddThh:mm');
                fieldTestPagerEntity.zonedDateTimeJade = this.datePipe
                    .transform(fieldTestPagerEntity.zonedDateTimeJade, 'yyyy-MM-ddThh:mm');
                fieldTestPagerEntity.zonedDateTimeRequiredJade = this.datePipe
                    .transform(fieldTestPagerEntity.zonedDateTimeRequiredJade, 'yyyy-MM-ddThh:mm');
                this.fieldTestPagerEntityModalRef(component, fieldTestPagerEntity);
            });
        } else {
            return this.fieldTestPagerEntityModalRef(component, new FieldTestPagerEntity());
        }
    }

    fieldTestPagerEntityModalRef(component: Component, fieldTestPagerEntity: FieldTestPagerEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestPagerEntity = fieldTestPagerEntity;
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
