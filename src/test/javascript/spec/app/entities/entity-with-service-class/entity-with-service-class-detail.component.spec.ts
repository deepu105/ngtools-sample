import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceClassDetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-class/entity-with-service-class-detail.component';
import { EntityWithServiceClassService } from '../../../../../../main/webapp/app/entities/entity-with-service-class/entity-with-service-class.service';
import { EntityWithServiceClass } from '../../../../../../main/webapp/app/entities/entity-with-service-class/entity-with-service-class.model';

describe('Component Tests', () => {

    describe('EntityWithServiceClass Management Detail Component', () => {
        let comp: EntityWithServiceClassDetailComponent;
        let fixture: ComponentFixture<EntityWithServiceClassDetailComponent>;
        let service: EntityWithServiceClassService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceClassDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceClassService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceClassDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceClassDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceClassService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceClass(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceClass).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
