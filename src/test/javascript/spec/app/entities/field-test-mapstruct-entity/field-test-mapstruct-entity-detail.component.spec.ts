import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestMapstructEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-mapstruct-entity/field-test-mapstruct-entity-detail.component';
import { FieldTestMapstructEntityService } from '../../../../../../main/webapp/app/entities/field-test-mapstruct-entity/field-test-mapstruct-entity.service';
import { FieldTestMapstructEntity } from '../../../../../../main/webapp/app/entities/field-test-mapstruct-entity/field-test-mapstruct-entity.model';

describe('Component Tests', () => {

    describe('FieldTestMapstructEntity Management Detail Component', () => {
        let comp: FieldTestMapstructEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestMapstructEntityDetailComponent>;
        let service: FieldTestMapstructEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestMapstructEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestMapstructEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestMapstructEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestMapstructEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestMapstructEntityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestMapstructEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestMapstructEntity).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
