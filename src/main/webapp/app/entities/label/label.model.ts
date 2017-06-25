import { BaseEntity } from './../../shared';

export class Label implements BaseEntity {
    constructor(
        public id?: number,
        public labelName?: string,
        public operations?: BaseEntity[],
    ) {
    }
}
