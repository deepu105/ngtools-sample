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

export class FieldTestEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringTom?: string,
        public stringRequiredTom?: string,
        public stringMinlengthTom?: string,
        public stringMaxlengthTom?: string,
        public stringPatternTom?: string,
        public integerTom?: number,
        public integerRequiredTom?: number,
        public integerMinTom?: number,
        public integerMaxTom?: number,
        public longTom?: number,
        public longRequiredTom?: number,
        public longMinTom?: number,
        public longMaxTom?: number,
        public floatTom?: number,
        public floatRequiredTom?: number,
        public floatMinTom?: number,
        public floatMaxTom?: number,
        public doubleRequiredTom?: number,
        public doubleMinTom?: number,
        public doubleMaxTom?: number,
        public bigDecimalRequiredTom?: number,
        public bigDecimalMinTom?: number,
        public bigDecimalMaxTom?: number,
        public localDateTom?: any,
        public localDateRequiredTom?: any,
        public instantTom?: any,
        public instanteRequiredTom?: any,
        public zonedDateTimeTom?: any,
        public zonedDateTimeRequiredTom?: any,
        public booleanTom?: boolean,
        public booleanRequiredTom?: boolean,
        public enumTom?: EnumFieldClass,
        public enumRequiredTom?: EnumRequiredFieldClass,
        public byteImageTomContentType?: string,
        public byteImageTom?: any,
        public byteImageRequiredTomContentType?: string,
        public byteImageRequiredTom?: any,
        public byteImageMinbytesTomContentType?: string,
        public byteImageMinbytesTom?: any,
        public byteImageMaxbytesTomContentType?: string,
        public byteImageMaxbytesTom?: any,
        public byteAnyTomContentType?: string,
        public byteAnyTom?: any,
        public byteAnyRequiredTomContentType?: string,
        public byteAnyRequiredTom?: any,
        public byteAnyMinbytesTomContentType?: string,
        public byteAnyMinbytesTom?: any,
        public byteAnyMaxbytesTomContentType?: string,
        public byteAnyMaxbytesTom?: any,
        public byteTextTom?: any,
        public byteTextRequiredTom?: any,
        public byteTextMinbytesTom?: any,
        public byteTextMaxbytesTom?: any,
    ) {
        this.booleanTom = false;
        this.booleanRequiredTom = false;
    }
}
