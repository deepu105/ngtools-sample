import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestInfiniteScrollEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-infinite-scroll-entity/field-test-infinite-scroll-entity-detail.component';
import { FieldTestInfiniteScrollEntityService } from '../../../../../../main/webapp/app/entities/field-test-infinite-scroll-entity/field-test-infinite-scroll-entity.service';
import { FieldTestInfiniteScrollEntity } from '../../../../../../main/webapp/app/entities/field-test-infinite-scroll-entity/field-test-infinite-scroll-entity.model';

describe('Component Tests', () => {

    describe('FieldTestInfiniteScrollEntity Management Detail Component', () => {
        let comp: FieldTestInfiniteScrollEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestInfiniteScrollEntityDetailComponent>;
        let service: FieldTestInfiniteScrollEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestInfiniteScrollEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestInfiniteScrollEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestInfiniteScrollEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestInfiniteScrollEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestInfiniteScrollEntityService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestInfiniteScrollEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestInfiniteScrollEntity).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
