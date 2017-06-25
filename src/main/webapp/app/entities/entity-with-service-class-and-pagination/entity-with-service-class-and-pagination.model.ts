import { BaseEntity } from './../../shared';

export class EntityWithServiceClassAndPagination implements BaseEntity {
    constructor(
        public id?: number,
        public enzo?: string,
    ) {
    }
}
