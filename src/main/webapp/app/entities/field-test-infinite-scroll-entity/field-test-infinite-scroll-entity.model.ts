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

export class FieldTestInfiniteScrollEntity implements BaseEntity {
    constructor(
        public id?: number,
        public stringHugo?: string,
        public stringRequiredHugo?: string,
        public stringMinlengthHugo?: string,
        public stringMaxlengthHugo?: string,
        public stringPatternHugo?: string,
        public integerHugo?: number,
        public integerRequiredHugo?: number,
        public integerMinHugo?: number,
        public integerMaxHugo?: number,
        public longHugo?: number,
        public longRequiredHugo?: number,
        public longMinHugo?: number,
        public longMaxHugo?: number,
        public floatHugo?: number,
        public floatRequiredHugo?: number,
        public floatMinHugo?: number,
        public floatMaxHugo?: number,
        public doubleRequiredHugo?: number,
        public doubleMinHugo?: number,
        public doubleMaxHugo?: number,
        public bigDecimalRequiredHugo?: number,
        public bigDecimalMinHugo?: number,
        public bigDecimalMaxHugo?: number,
        public localDateHugo?: any,
        public localDateRequiredHugo?: any,
        public instantHugo?: any,
        public instanteRequiredHugo?: any,
        public zonedDateTimeHugo?: any,
        public zonedDateTimeRequiredHugo?: any,
        public booleanHugo?: boolean,
        public booleanRequiredHugo?: boolean,
        public enumHugo?: EnumFieldClass,
        public enumRequiredHugo?: EnumRequiredFieldClass,
        public byteImageHugoContentType?: string,
        public byteImageHugo?: any,
        public byteImageRequiredHugoContentType?: string,
        public byteImageRequiredHugo?: any,
        public byteImageMinbytesHugoContentType?: string,
        public byteImageMinbytesHugo?: any,
        public byteImageMaxbytesHugoContentType?: string,
        public byteImageMaxbytesHugo?: any,
        public byteAnyHugoContentType?: string,
        public byteAnyHugo?: any,
        public byteAnyRequiredHugoContentType?: string,
        public byteAnyRequiredHugo?: any,
        public byteAnyMinbytesHugoContentType?: string,
        public byteAnyMinbytesHugo?: any,
        public byteAnyMaxbytesHugoContentType?: string,
        public byteAnyMaxbytesHugo?: any,
        public byteTextHugo?: any,
        public byteTextRequiredHugo?: any,
        public byteTextMinbytesHugo?: any,
        public byteTextMaxbytesHugo?: any,
    ) {
        this.booleanHugo = false;
        this.booleanRequiredHugo = false;
    }
}
