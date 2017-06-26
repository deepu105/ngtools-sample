import { BaseEntity } from './../../shared';

const enum DivisionType {
    'SCHOOL',
    'CLASS',
    'SUBGROUP'
}

export class Division implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public shortName?: string,
        public numberOfPeople?: number,
        public divisionType?: DivisionType,
        public colorBackground?: string,
        public colorText?: string,
        public divisionsPlaces?: BaseEntity[],
        public preferredPlaces?: BaseEntity[],
    ) {
    }
}
