import { BaseEntity } from './../../shared';

export class EntityWithServiceClassAndDTO implements BaseEntity {
    constructor(
        public id?: number,
        public lucas?: string,
    ) {
    }
}
