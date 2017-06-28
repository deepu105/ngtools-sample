/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceImplDetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-impl/entity-with-service-impl-detail.component';
import { EntityWithServiceImplService } from '../../../../../../main/webapp/app/entities/entity-with-service-impl/entity-with-service-impl.service';
import { EntityWithServiceImpl } from '../../../../../../main/webapp/app/entities/entity-with-service-impl/entity-with-service-impl.model';

describe('Component Tests', () => {

    describe('EntityWithServiceImpl Management Detail Component', () => {
        let comp: EntityWithServiceImplDetailComponent;
        let fixture: ComponentFixture<EntityWithServiceImplDetailComponent>;
        let service: EntityWithServiceImplService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceImplDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceImplService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceImplDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceImplDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceImplService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceImpl(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceImpl).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
