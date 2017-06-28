import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FieldTestPagerEntityDetailComponent } from '../../../../../../main/webapp/app/entities/field-test-pager-entity/field-test-pager-entity-detail.component';
import { FieldTestPagerEntityService } from '../../../../../../main/webapp/app/entities/field-test-pager-entity/field-test-pager-entity.service';
import { FieldTestPagerEntity } from '../../../../../../main/webapp/app/entities/field-test-pager-entity/field-test-pager-entity.model';

describe('Component Tests', () => {

    describe('FieldTestPagerEntity Management Detail Component', () => {
        let comp: FieldTestPagerEntityDetailComponent;
        let fixture: ComponentFixture<FieldTestPagerEntityDetailComponent>;
        let service: FieldTestPagerEntityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [FieldTestPagerEntityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    FieldTestPagerEntityService,
                    JhiEventManager
                ]
            }).overrideTemplate(FieldTestPagerEntityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FieldTestPagerEntityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FieldTestPagerEntityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new FieldTestPagerEntity(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.fieldTestPagerEntity).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
