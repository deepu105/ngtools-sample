import { BaseEntity } from './../../shared';

export class EntityWithPagination implements BaseEntity {
    constructor(
        public id?: number,
        public nathan?: string,
    ) {
    }
}
