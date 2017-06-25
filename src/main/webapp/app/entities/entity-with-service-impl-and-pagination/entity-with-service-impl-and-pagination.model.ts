import { BaseEntity } from './../../shared';

export class EntityWithServiceImplAndPagination implements BaseEntity {
    constructor(
        public id?: number,
        public hugo?: string,
    ) {
    }
}
