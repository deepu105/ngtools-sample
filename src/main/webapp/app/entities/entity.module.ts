import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Jh4TestBankAccountModule } from './bank-account/bank-account.module';
import { Jh4TestLabelModule } from './label/label.module';
import { Jh4TestOperationModule } from './operation/operation.module';
import { Jh4TestFieldTestEntityModule } from './field-test-entity/field-test-entity.module';
import { Jh4TestFieldTestPagerEntityModule } from './field-test-pager-entity/field-test-pager-entity.module';
import { Jh4TestFieldTestMapstructEntityModule } from './field-test-mapstruct-entity/field-test-mapstruct-entity.module';
import { Jh4TestFieldTestServiceImplEntityModule } from './field-test-service-impl-entity/field-test-service-impl-entity.module';
import { Jh4TestFieldTestInfiniteScrollEntityModule } from './field-test-infinite-scroll-entity/field-test-infinite-scroll-entity.module';
import { Jh4TestFieldTestServiceClassEntityModule } from './field-test-service-class-entity/field-test-service-class-entity.module';
import { Jh4TestFieldTestPaginationEntityModule } from './field-test-pagination-entity/field-test-pagination-entity.module';
import { Jh4TestEntityWithDTOModule } from './entity-with-dto/entity-with-dto.module';
import { Jh4TestEntityWithServiceClassModule } from './entity-with-service-class/entity-with-service-class.module';
import { Jh4TestEntityWithServiceImplModule } from './entity-with-service-impl/entity-with-service-impl.module';
import { Jh4TestEntityWithPaginationModule } from './entity-with-pagination/entity-with-pagination.module';
import { Jh4TestEntityWithServiceClassAndPaginationModule } from './entity-with-service-class-and-pagination/entity-with-service-class-and-pagination.module';
import { Jh4TestEntityWithServiceImplAndPaginationModule } from './entity-with-service-impl-and-pagination/entity-with-service-impl-and-pagination.module';
import { Jh4TestEntityWithServiceClassAndDTOModule } from './entity-with-service-class-and-dto/entity-with-service-class-and-dto.module';
import { Jh4TestEntityWithServiceImplAndDTOModule } from './entity-with-service-impl-and-dto/entity-with-service-impl-and-dto.module';
import { Jh4TestEntityWithPaginationAndDTOModule } from './entity-with-pagination-and-dto/entity-with-pagination-and-dto.module';
import { Jh4TestEntityWithServiceClassPaginationAndDTOModule } from './entity-with-service-class-pagination-and-dto/entity-with-service-class-pagination-and-dto.module';
import { Jh4TestEntityWithServiceImplPaginationAndDTOModule } from './entity-with-service-impl-pagination-and-dto/entity-with-service-impl-pagination-and-dto.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        Jh4TestBankAccountModule,
        Jh4TestLabelModule,
        Jh4TestOperationModule,
        Jh4TestFieldTestEntityModule,
        Jh4TestFieldTestPagerEntityModule,
        Jh4TestFieldTestMapstructEntityModule,
        Jh4TestFieldTestServiceImplEntityModule,
        Jh4TestFieldTestInfiniteScrollEntityModule,
        Jh4TestFieldTestServiceClassEntityModule,
        Jh4TestFieldTestPaginationEntityModule,
        Jh4TestEntityWithDTOModule,
        Jh4TestEntityWithServiceClassModule,
        Jh4TestEntityWithServiceImplModule,
        Jh4TestEntityWithPaginationModule,
        Jh4TestEntityWithServiceClassAndPaginationModule,
        Jh4TestEntityWithServiceImplAndPaginationModule,
        Jh4TestEntityWithServiceClassAndDTOModule,
        Jh4TestEntityWithServiceImplAndDTOModule,
        Jh4TestEntityWithPaginationAndDTOModule,
        Jh4TestEntityWithServiceClassPaginationAndDTOModule,
        Jh4TestEntityWithServiceImplPaginationAndDTOModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jh4TestEntityModule {}
