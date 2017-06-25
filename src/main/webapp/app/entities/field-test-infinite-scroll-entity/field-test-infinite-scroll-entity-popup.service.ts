import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { FieldTestInfiniteScrollEntity } from './field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityService } from './field-test-infinite-scroll-entity.service';

@Injectable()
export class FieldTestInfiniteScrollEntityPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fieldTestInfiniteScrollEntityService: FieldTestInfiniteScrollEntityService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.fieldTestInfiniteScrollEntityService.find(id).subscribe((fieldTestInfiniteScrollEntity) => {
                if (fieldTestInfiniteScrollEntity.localDateHugo) {
                    fieldTestInfiniteScrollEntity.localDateHugo = {
                        year: fieldTestInfiniteScrollEntity.localDateHugo.getFullYear(),
                        month: fieldTestInfiniteScrollEntity.localDateHugo.getMonth() + 1,
                        day: fieldTestInfiniteScrollEntity.localDateHugo.getDate()
                    };
                }
                if (fieldTestInfiniteScrollEntity.localDateRequiredHugo) {
                    fieldTestInfiniteScrollEntity.localDateRequiredHugo = {
                        year: fieldTestInfiniteScrollEntity.localDateRequiredHugo.getFullYear(),
                        month: fieldTestInfiniteScrollEntity.localDateRequiredHugo.getMonth() + 1,
                        day: fieldTestInfiniteScrollEntity.localDateRequiredHugo.getDate()
                    };
                }
                fieldTestInfiniteScrollEntity.instantHugo = this.datePipe
                    .transform(fieldTestInfiniteScrollEntity.instantHugo, 'yyyy-MM-ddThh:mm');
                fieldTestInfiniteScrollEntity.instanteRequiredHugo = this.datePipe
                    .transform(fieldTestInfiniteScrollEntity.instanteRequiredHugo, 'yyyy-MM-ddThh:mm');
                fieldTestInfiniteScrollEntity.zonedDateTimeHugo = this.datePipe
                    .transform(fieldTestInfiniteScrollEntity.zonedDateTimeHugo, 'yyyy-MM-ddThh:mm');
                fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo = this.datePipe
                    .transform(fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo, 'yyyy-MM-ddThh:mm');
                this.fieldTestInfiniteScrollEntityModalRef(component, fieldTestInfiniteScrollEntity);
            });
        } else {
            return this.fieldTestInfiniteScrollEntityModalRef(component, new FieldTestInfiniteScrollEntity());
        }
    }

    fieldTestInfiniteScrollEntityModalRef(component: Component, fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntity;
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
