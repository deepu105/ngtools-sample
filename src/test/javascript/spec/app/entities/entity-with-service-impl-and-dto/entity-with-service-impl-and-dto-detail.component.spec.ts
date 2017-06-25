import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceImplAndDTODetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-and-dto/entity-with-service-impl-and-dto-detail.component';
import { EntityWithServiceImplAndDTOService } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-and-dto/entity-with-service-impl-and-dto.service';
import { EntityWithServiceImplAndDTO } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-and-dto/entity-with-service-impl-and-dto.model';

describe('Component Tests', () => {

    describe('EntityWithServiceImplAndDTO Management Detail Component', () => {
        let comp: EntityWithServiceImplAndDTODetailComponent;
        let fixture: ComponentFixture<EntityWithServiceImplAndDTODetailComponent>;
        let service: EntityWithServiceImplAndDTOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceImplAndDTODetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceImplAndDTOService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceImplAndDTODetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceImplAndDTODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceImplAndDTOService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceImplAndDTO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceImplAndDTO).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
