/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceClassAndPaginationDetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-class-and-pagination/entity-with-service-class-and-pagination-detail.component';
import { EntityWithServiceClassAndPaginationService } from '../../../../../../main/webapp/app/entities/entity-with-service-class-and-pagination/entity-with-service-class-and-pagination.service';
import { EntityWithServiceClassAndPagination } from '../../../../../../main/webapp/app/entities/entity-with-service-class-and-pagination/entity-with-service-class-and-pagination.model';

describe('Component Tests', () => {

    describe('EntityWithServiceClassAndPagination Management Detail Component', () => {
        let comp: EntityWithServiceClassAndPaginationDetailComponent;
        let fixture: ComponentFixture<EntityWithServiceClassAndPaginationDetailComponent>;
        let service: EntityWithServiceClassAndPaginationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceClassAndPaginationDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceClassAndPaginationService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceClassAndPaginationDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceClassAndPaginationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceClassAndPaginationService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceClassAndPagination(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceClassAndPagination).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
