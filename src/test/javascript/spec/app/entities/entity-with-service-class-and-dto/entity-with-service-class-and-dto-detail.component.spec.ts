/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceClassAndDTODetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-class-and-dto/entity-with-service-class-and-dto-detail.component';
import { EntityWithServiceClassAndDTOService } from '../../../../../../main/webapp/app/entities/entity-with-service-class-and-dto/entity-with-service-class-and-dto.service';
import { EntityWithServiceClassAndDTO } from '../../../../../../main/webapp/app/entities/entity-with-service-class-and-dto/entity-with-service-class-and-dto.model';

describe('Component Tests', () => {

    describe('EntityWithServiceClassAndDTO Management Detail Component', () => {
        let comp: EntityWithServiceClassAndDTODetailComponent;
        let fixture: ComponentFixture<EntityWithServiceClassAndDTODetailComponent>;
        let service: EntityWithServiceClassAndDTOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceClassAndDTODetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceClassAndDTOService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceClassAndDTODetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceClassAndDTODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceClassAndDTOService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceClassAndDTO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceClassAndDTO).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
