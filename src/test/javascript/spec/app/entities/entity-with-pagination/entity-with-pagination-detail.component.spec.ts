import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithPaginationDetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-pagination/entity-with-pagination-detail.component';
import { EntityWithPaginationService } from '../../../../../../main/webapp/app/entities/entity-with-pagination/entity-with-pagination.service';
import { EntityWithPagination } from '../../../../../../main/webapp/app/entities/entity-with-pagination/entity-with-pagination.model';

describe('Component Tests', () => {

    describe('EntityWithPagination Management Detail Component', () => {
        let comp: EntityWithPaginationDetailComponent;
        let fixture: ComponentFixture<EntityWithPaginationDetailComponent>;
        let service: EntityWithPaginationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithPaginationDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithPaginationService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithPaginationDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithPaginationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithPaginationService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithPagination(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithPagination).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
