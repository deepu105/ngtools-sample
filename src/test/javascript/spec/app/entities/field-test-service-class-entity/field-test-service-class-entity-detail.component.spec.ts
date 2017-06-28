/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestServiceClassEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-service-class-entity/field-test-service-class-entity-detail.component';
import { FieldTestServiceClassEntityService } from '../../../../../../main/webapp/app/entities/field-test-service-class-entity/field-test-service-class-entity.service';
import { FieldTestServiceClassEntity } from '../../../../../../main/webapp/app/entities/field-test-service-class-entity/field-test-service-class-entity.model';

describe('Component Tests', () => {

    describe('FieldTestServiceClassEntity Management Detail Component', () => {
        let comp: FieldTestServiceClassEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestServiceClassEntityDetailComponent>;
        let service: FieldTestServiceClassEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestServiceClassEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestServiceClassEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestServiceClassEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestServiceClassEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestServiceClassEntityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestServiceClassEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestServiceClassEntity).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
