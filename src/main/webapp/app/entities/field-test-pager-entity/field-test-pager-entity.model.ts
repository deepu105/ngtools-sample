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

export class FieldTestPagerEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringJade?: string,
        public stringRequiredJade?: string,
        public stringMinlengthJade?: string,
        public stringMaxlengthJade?: string,
        public stringPatternJade?: string,
        public integerJade?: number,
        public integerRequiredJade?: number,
        public integerMinJade?: number,
        public integerMaxJade?: number,
        public longJade?: number,
        public longRequiredJade?: number,
        public longMinJade?: number,
        public longMaxJade?: number,
        public floatJade?: number,
        public floatRequiredJade?: number,
        public floatMinJade?: number,
        public floatMaxJade?: number,
        public doubleRequiredJade?: number,
        public doubleMinJade?: number,
        public doubleMaxJade?: number,
        public bigDecimalRequiredJade?: number,
        public bigDecimalMinJade?: number,
        public bigDecimalMaxJade?: number,
        public localDateJade?: any,
        public localDateRequiredJade?: any,
        public instantJade?: any,
        public instanteRequiredJade?: any,
        public zonedDateTimeJade?: any,
        public zonedDateTimeRequiredJade?: any,
        public booleanJade?: boolean,
        public booleanRequiredJade?: boolean,
        public enumJade?: EnumFieldClass,
        public enumRequiredJade?: EnumRequiredFieldClass,
        public byteImageJadeContentType?: string,
        public byteImageJade?: any,
        public byteImageRequiredJadeContentType?: string,
        public byteImageRequiredJade?: any,
        public byteImageMinbytesJadeContentType?: string,
        public byteImageMinbytesJade?: any,
        public byteImageMaxbytesJadeContentType?: string,
        public byteImageMaxbytesJade?: any,
        public byteAnyJadeContentType?: string,
        public byteAnyJade?: any,
        public byteAnyRequiredJadeContentType?: string,
        public byteAnyRequiredJade?: any,
        public byteAnyMinbytesJadeContentType?: string,
        public byteAnyMinbytesJade?: any,
        public byteAnyMaxbytesJadeContentType?: string,
        public byteAnyMaxbytesJade?: any,
        public byteTextJade?: any,
        public byteTextRequiredJade?: any,
        public byteTextMinbytesJade?: any,
        public byteTextMaxbytesJade?: any,
    ) {
        this.booleanJade = false;
        this.booleanRequiredJade = false;
    }
}
