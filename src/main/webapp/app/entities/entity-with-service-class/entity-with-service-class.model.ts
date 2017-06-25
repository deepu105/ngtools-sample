import { BaseEntity } from './../../shared';

export class EntityWithServiceClass implements BaseEntity {
    constructor(
        public id?: number,
        public zoe?: string,
    ) {
    }
}
