import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceImplAndPaginationDetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-and-pagination/entity-with-service-impl-and-pagination-detail.component';
import { EntityWithServiceImplAndPaginationService } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-and-pagination/entity-with-service-impl-and-pagination.service';
import { EntityWithServiceImplAndPagination } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-and-pagination/entity-with-service-impl-and-pagination.model';

describe('Component Tests', () => {

    describe('EntityWithServiceImplAndPagination Management Detail Component', () => {
        let comp: EntityWithServiceImplAndPaginationDetailComponent;
        let fixture: ComponentFixture<EntityWithServiceImplAndPaginationDetailComponent>;
        let service: EntityWithServiceImplAndPaginationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceImplAndPaginationDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceImplAndPaginationService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceImplAndPaginationDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceImplAndPaginationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceImplAndPaginationService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceImplAndPagination(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceImplAndPagination).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
