import { BaseEntity } from './../../shared';

export class Place implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public numberOfSeats?: number,
        public shortName?: string,
        public colorBackground?: string,
        public colorText?: string,
        public preferredDivisions?: BaseEntity[],
        public owner?: BaseEntity,
    ) {
    }
}
