import { BaseEntity } from './../../shared';

export class EntityWithServiceImpl implements BaseEntity {
    constructor(
        public id?: number,
        public clara?: string,
    ) {
    }
}
