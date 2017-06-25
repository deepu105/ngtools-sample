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

export class FieldTestServiceImplEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringMika?: string,
        public stringRequiredMika?: string,
        public stringMinlengthMika?: string,
        public stringMaxlengthMika?: string,
        public stringPatternMika?: string,
        public integerMika?: number,
        public integerRequiredMika?: number,
        public integerMinMika?: number,
        public integerMaxMika?: number,
        public longMika?: number,
        public longRequiredMika?: number,
        public longMinMika?: number,
        public longMaxMika?: number,
        public floatMika?: number,
        public floatRequiredMika?: number,
        public floatMinMika?: number,
        public floatMaxMika?: number,
        public doubleRequiredMika?: number,
        public doubleMinMika?: number,
        public doubleMaxMika?: number,
        public bigDecimalRequiredMika?: number,
        public bigDecimalMinMika?: number,
        public bigDecimalMaxMika?: number,
        public localDateMika?: any,
        public localDateRequiredMika?: any,
        public instantMika?: any,
        public instanteRequiredMika?: any,
        public zonedDateTimeMika?: any,
        public zonedDateTimeRequiredMika?: any,
        public booleanMika?: boolean,
        public booleanRequiredMika?: boolean,
        public enumMika?: EnumFieldClass,
        public enumRequiredMika?: EnumRequiredFieldClass,
        public byteImageMikaContentType?: string,
        public byteImageMika?: any,
        public byteImageRequiredMikaContentType?: string,
        public byteImageRequiredMika?: any,
        public byteImageMinbytesMikaContentType?: string,
        public byteImageMinbytesMika?: any,
        public byteImageMaxbytesMikaContentType?: string,
        public byteImageMaxbytesMika?: any,
        public byteAnyMikaContentType?: string,
        public byteAnyMika?: any,
        public byteAnyRequiredMikaContentType?: string,
        public byteAnyRequiredMika?: any,
        public byteAnyMinbytesMikaContentType?: string,
        public byteAnyMinbytesMika?: any,
        public byteAnyMaxbytesMikaContentType?: string,
        public byteAnyMaxbytesMika?: any,
        public byteTextMika?: any,
        public byteTextRequiredMika?: any,
        public byteTextMinbytesMika?: any,
        public byteTextMaxbytesMika?: any,
    ) {
        this.booleanMika = false;
        this.booleanRequiredMika = false;
    }
}
