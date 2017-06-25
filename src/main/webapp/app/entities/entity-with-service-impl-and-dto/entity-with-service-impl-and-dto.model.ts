import { BaseEntity } from './../../shared';

export class EntityWithServiceImplAndDTO implements BaseEntity {
    constructor(
        public id?: number,
        public louis?: string,
    ) {
    }
}
