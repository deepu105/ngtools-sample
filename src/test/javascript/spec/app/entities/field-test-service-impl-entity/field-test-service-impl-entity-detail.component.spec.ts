/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestServiceImplEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-service-impl-entity/field-test-service-impl-entity-detail.component';
import { FieldTestServiceImplEntityService } from '../../../../../../main/webapp/app/entities/field-test-service-impl-entity/field-test-service-impl-entity.service';
import { FieldTestServiceImplEntity } from '../../../../../../main/webapp/app/entities/field-test-service-impl-entity/field-test-service-impl-entity.model';

describe('Component Tests', () => {

    describe('FieldTestServiceImplEntity Management Detail Component', () => {
        let comp: FieldTestServiceImplEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestServiceImplEntityDetailComponent>;
        let service: FieldTestServiceImplEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestServiceImplEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestServiceImplEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestServiceImplEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestServiceImplEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestServiceImplEntityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestServiceImplEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestServiceImplEntity).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
