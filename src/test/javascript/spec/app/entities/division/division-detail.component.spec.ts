import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DivisionDetailComponent } from '../../../../../../main/webapp/app/entities/division/division-detail.component';
import { DivisionService } from '../../../../../../main/webapp/app/entities/division/division.service';
import { Division } from '../../../../../../main/webapp/app/entities/division/division.model';

describe('Component Tests', () => {

    describe('Division Management Detail Component', () => {
        let comp: DivisionDetailComponent;
        let fixture: ComponentFixture<DivisionDetailComponent>;
        let service: DivisionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [DivisionDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DivisionService,
                    JhiEventManager
                ]
            }).overrideTemplate(DivisionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DivisionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DivisionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Division(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.division).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
