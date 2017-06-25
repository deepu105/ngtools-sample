import { BaseEntity } from './../../shared';

const enum EnumFieldClass {
    'ENUM_VALUE_1',
    'ENUM_VALUE_2',
    'ENUM_VALUE_3'
}

const enum EnumRequiredFieldClass {
    'ENUM_VALUE_1',
    'ENUM_VALUE_2',
    'ENUM_VALUE_3'
}

export class FieldTestPaginationEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringAlice?: string,
        public stringRequiredAlice?: string,
        public stringMinlengthAlice?: string,
        public stringMaxlengthAlice?: string,
        public stringPatternAlice?: string,
        public integerAlice?: number,
        public integerRequiredAlice?: number,
        public integerMinAlice?: number,
        public integerMaxAlice?: number,
        public longAlice?: number,
        public longRequiredAlice?: number,
        public longMinAlice?: number,
        public longMaxAlice?: number,
        public floatAlice?: number,
        public floatRequiredAlice?: number,
        public floatMinAlice?: number,
        public floatMaxAlice?: number,
        public doubleRequiredAlice?: number,
        public doubleMinAlice?: number,
        public doubleMaxAlice?: number,
        public bigDecimalRequiredAlice?: number,
        public bigDecimalMinAlice?: number,
        public bigDecimalMaxAlice?: number,
        public localDateAlice?: any,
        public localDateRequiredAlice?: any,
        public instantAlice?: any,
        public instanteRequiredAlice?: any,
        public zonedDateTimeAlice?: any,
        public zonedDateTimeRequiredAlice?: any,
        public booleanAlice?: boolean,
        public booleanRequiredAlice?: boolean,
        public enumAlice?: EnumFieldClass,
        public enumRequiredAlice?: EnumRequiredFieldClass,
        public byteImageAliceContentType?: string,
        public byteImageAlice?: any,
        public byteImageRequiredAliceContentType?: string,
        public byteImageRequiredAlice?: any,
        public byteImageMinbytesAliceContentType?: string,
        public byteImageMinbytesAlice?: any,
        public byteImageMaxbytesAliceContentType?: string,
        public byteImageMaxbytesAlice?: any,
        public byteAnyAliceContentType?: string,
        public byteAnyAlice?: any,
        public byteAnyRequiredAliceContentType?: string,
        public byteAnyRequiredAlice?: any,
        public byteAnyMinbytesAliceContentType?: string,
        public byteAnyMinbytesAlice?: any,
        public byteAnyMaxbytesAliceContentType?: string,
        public byteAnyMaxbytesAlice?: any,
        public byteTextAlice?: any,
        public byteTextRequiredAlice?: any,
        public byteTextMinbytesAlice?: any,
        public byteTextMaxbytesAlice?: any,
    ) {
        this.booleanAlice = false;
        this.booleanRequiredAlice = false;
    }
}
