import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestServiceClassEntity } from './field-test-service-class-entity.model';
import { FieldTestServiceClassEntityService } from './field-test-service-class-entity.service';

@Injectable()
export class FieldTestServiceClassEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestServiceClassEntityService: FieldTestServiceClassEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestServiceClassEntityService.find(id).subscribe((fieldTestServiceClassEntity) => {
                if (fieldTestServiceClassEntity.localDateBob) {
                    fieldTestServiceClassEntity.localDateBob = {
                        year: fieldTestServiceClassEntity.localDateBob.getFullYear(),
                        month: fieldTestServiceClassEntity.localDateBob.getMonth() + 1,
                        day: fieldTestServiceClassEntity.localDateBob.getDate()
                    };
                }
                if (fieldTestServiceClassEntity.localDateRequiredBob) {
                    fieldTestServiceClassEntity.localDateRequiredBob = {
                        year: fieldTestServiceClassEntity.localDateRequiredBob.getFullYear(),
                        month: fieldTestServiceClassEntity.localDateRequiredBob.getMonth() + 1,
                        day: fieldTestServiceClassEntity.localDateRequiredBob.getDate()
                    };
                }
                fieldTestServiceClassEntity.instantBob = this.datePipe
                    .transform(fieldTestServiceClassEntity.instantBob, 'yyyy-MM-ddThh:mm');
                fieldTestServiceClassEntity.instanteRequiredBob = this.datePipe
                    .transform(fieldTestServiceClassEntity.instanteRequiredBob, 'yyyy-MM-ddThh:mm');
                fieldTestServiceClassEntity.zonedDateTimeBob = this.datePipe
                    .transform(fieldTestServiceClassEntity.zonedDateTimeBob, 'yyyy-MM-ddThh:mm');
                fieldTestServiceClassEntity.zonedDateTimeRequiredBob = this.datePipe
                    .transform(fieldTestServiceClassEntity.zonedDateTimeRequiredBob, 'yyyy-MM-ddThh:mm');
                this.fieldTestServiceClassEntityModalRef(component, fieldTestServiceClassEntity);
            });
        } else {
            return this.fieldTestServiceClassEntityModalRef(component, new FieldTestServiceClassEntity());
        }
    }

    fieldTestServiceClassEntityModalRef(component: Component, fieldTestServiceClassEntity: FieldTestServiceClassEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestServiceClassEntity = fieldTestServiceClassEntity;
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
