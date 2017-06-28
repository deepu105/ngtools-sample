/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PlaceDetailComponent } from '../../../../../../main/webapp/app/entities/place/place-detail.component';
import { PlaceService } from '../../../../../../main/webapp/app/entities/place/place.service';
import { Place } from '../../../../../../main/webapp/app/entities/place/place.model';

describe('Component Tests', () => {

    describe('Place Management Detail Component', () => {
        let comp: PlaceDetailComponent;
        let fixture: ComponentFixture<PlaceDetailComponent>;
        let service: PlaceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [PlaceDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PlaceService,
                    JhiEventManager
                ]
            }).overrideTemplate(PlaceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PlaceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlaceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Place(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.place).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
