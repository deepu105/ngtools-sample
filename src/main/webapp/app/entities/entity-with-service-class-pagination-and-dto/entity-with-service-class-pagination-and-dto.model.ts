import { BaseEntity } from './../../shared';

export class EntityWithServiceClassPaginationAndDTO implements BaseEntity {
    constructor(
        public id?: number,
        public lena?: string,
    ) {
    }
}
