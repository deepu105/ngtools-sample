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

export class FieldTestMapstructEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringEva?: string,
        public stringRequiredEva?: string,
        public stringMinlengthEva?: string,
        public stringMaxlengthEva?: string,
        public stringPatternEva?: string,
        public integerEva?: number,
        public integerRequiredEva?: number,
        public integerMinEva?: number,
        public integerMaxEva?: number,
        public longEva?: number,
        public longRequiredEva?: number,
        public longMinEva?: number,
        public longMaxEva?: number,
        public floatEva?: number,
        public floatRequiredEva?: number,
        public floatMinEva?: number,
        public floatMaxEva?: number,
        public doubleRequiredEva?: number,
        public doubleMinEva?: number,
        public doubleMaxEva?: number,
        public bigDecimalRequiredEva?: number,
        public bigDecimalMinEva?: number,
        public bigDecimalMaxEva?: number,
        public localDateEva?: any,
        public localDateRequiredEva?: any,
        public instantEva?: any,
        public instanteRequiredEva?: any,
        public zonedDateTimeEva?: any,
        public zonedDateTimeRequiredEva?: any,
        public booleanEva?: boolean,
        public booleanRequiredEva?: boolean,
        public enumEva?: EnumFieldClass,
        public enumRequiredEva?: EnumRequiredFieldClass,
        public byteImageEvaContentType?: string,
        public byteImageEva?: any,
        public byteImageRequiredEvaContentType?: string,
        public byteImageRequiredEva?: any,
        public byteImageMinbytesEvaContentType?: string,
        public byteImageMinbytesEva?: any,
        public byteImageMaxbytesEvaContentType?: string,
        public byteImageMaxbytesEva?: any,
        public byteAnyEvaContentType?: string,
        public byteAnyEva?: any,
        public byteAnyRequiredEvaContentType?: string,
        public byteAnyRequiredEva?: any,
        public byteAnyMinbytesEvaContentType?: string,
        public byteAnyMinbytesEva?: any,
        public byteAnyMaxbytesEvaContentType?: string,
        public byteAnyMaxbytesEva?: any,
        public byteTextEva?: any,
        public byteTextRequiredEva?: any,
        public byteTextMinbytesEva?: any,
        public byteTextMaxbytesEva?: any,
    ) {
        this.booleanEva = false;
        this.booleanRequiredEva = false;
    }
}
