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

export class FieldTestServiceClassEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringBob?: string,
        public stringRequiredBob?: string,
        public stringMinlengthBob?: string,
        public stringMaxlengthBob?: string,
        public stringPatternBob?: string,
        public integerBob?: number,
        public integerRequiredBob?: number,
        public integerMinBob?: number,
        public integerMaxBob?: number,
        public longBob?: number,
        public longRequiredBob?: number,
        public longMinBob?: number,
        public longMaxBob?: number,
        public floatBob?: number,
        public floatRequiredBob?: number,
        public floatMinBob?: number,
        public floatMaxBob?: number,
        public doubleRequiredBob?: number,
        public doubleMinBob?: number,
        public doubleMaxBob?: number,
        public bigDecimalRequiredBob?: number,
        public bigDecimalMinBob?: number,
        public bigDecimalMaxBob?: number,
        public localDateBob?: any,
        public localDateRequiredBob?: any,
        public instantBob?: any,
        public instanteRequiredBob?: any,
        public zonedDateTimeBob?: any,
        public zonedDateTimeRequiredBob?: any,
        public booleanBob?: boolean,
        public booleanRequiredBob?: boolean,
        public enumBob?: EnumFieldClass,
        public enumRequiredBob?: EnumRequiredFieldClass,
        public byteImageBobContentType?: string,
        public byteImageBob?: any,
        public byteImageRequiredBobContentType?: string,
        public byteImageRequiredBob?: any,
        public byteImageMinbytesBobContentType?: string,
        public byteImageMinbytesBob?: any,
        public byteImageMaxbytesBobContentType?: string,
        public byteImageMaxbytesBob?: any,
        public byteAnyBobContentType?: string,
        public byteAnyBob?: any,
        public byteAnyRequiredBobContentType?: string,
        public byteAnyRequiredBob?: any,
        public byteAnyMinbytesBobContentType?: string,
        public byteAnyMinbytesBob?: any,
        public byteAnyMaxbytesBobContentType?: string,
        public byteAnyMaxbytesBob?: any,
        public byteTextBob?: any,
        public byteTextRequiredBob?: any,
        public byteTextMinbytesBob?: any,
        public byteTextMaxbytesBob?: any,
    ) {
        this.booleanBob = false;
        this.booleanRequiredBob = false;
    }
}
