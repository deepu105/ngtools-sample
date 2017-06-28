/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-entity/field-test-entity-detail.component';
import { FieldTestEntityService } from '../../../../../../main/webapp/app/entities/field-test-entity/field-test-entity.service';
import { FieldTestEntity } from '../../../../../../main/webapp/app/entities/field-test-entity/field-test-entity.model';

describe('Component Tests', () => {

    describe('FieldTestEntity Management Detail Component', () => {
        let comp: FieldTestEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestEntityDetailComponent>;
        let service: FieldTestEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestEntityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestEntity).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
