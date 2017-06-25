import { BaseEntity } from './../../shared';

export class EntityWithPaginationAndDTO implements BaseEntity {
    constructor(
        public id?: number,
        public lea?: string,
    ) {
    }
}
