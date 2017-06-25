import { BaseEntity } from './../../shared';

const enum BankAccountType {
    'CHECKING',
    'SAVINGS',
    'LOAN'
}

export class BankAccount implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public bankNumber?: number,
        public agencyNumber?: number,
        public lastOperationDuration?: number,
        public meanOperationDuration?: number,
        public balance?: number,
        public openingDay?: any,
        public lastOperationDate?: any,
        public active?: boolean,
        public accountType?: BankAccountType,
        public attachmentContentType?: string,
        public attachment?: any,
        public description?: any,
        public userId?: number,
        public operations?: BaseEntity[],
    ) {
        this.active = false;
    }
}
