/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestPaginationEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-pagination-entity/field-test-pagination-entity-detail.component';
import { FieldTestPaginationEntityService } from '../../../../../../main/webapp/app/entities/field-test-pagination-entity/field-test-pagination-entity.service';
import { FieldTestPaginationEntity } from '../../../../../../main/webapp/app/entities/field-test-pagination-entity/field-test-pagination-entity.model';

describe('Component Tests', () => {

    describe('FieldTestPaginationEntity Management Detail Component', () => {
        let comp: FieldTestPaginationEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestPaginationEntityDetailComponent>;
        let service: FieldTestPaginationEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestPaginationEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestPaginationEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestPaginationEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestPaginationEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestPaginationEntityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestPaginationEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestPaginationEntity).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
