import { BaseEntity } from './../../shared';

export class EntityWithServiceImplPaginationAndDTO implements BaseEntity {
    constructor(
        public id?: number,
        public theo?: string,
    ) {
    }
}
