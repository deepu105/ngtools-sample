import { BaseEntity } from './../../shared';

export class EntityWithDTO implements BaseEntity {
    constructor(
        public id?: number,
        public emma?: string,
    ) {
    }
}
