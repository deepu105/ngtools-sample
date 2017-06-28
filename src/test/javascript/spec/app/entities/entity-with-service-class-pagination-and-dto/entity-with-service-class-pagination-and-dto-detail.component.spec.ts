/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceClassPaginationAndDTODetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-class-pagination-and-dto/entity-with-service-class-pagination-and-dto-detail.component';
import { EntityWithServiceClassPaginationAndDTOService } from '../../../../../../main/webapp/app/entities/entity-with-service-class-pagination-and-dto/entity-with-service-class-pagination-and-dto.service';
import { EntityWithServiceClassPaginationAndDTO } from '../../../../../../main/webapp/app/entities/entity-with-service-class-pagination-and-dto/entity-with-service-class-pagination-and-dto.model';

describe('Component Tests', () => {

    describe('EntityWithServiceClassPaginationAndDTO Management Detail Component', () => {
        let comp: EntityWithServiceClassPaginationAndDTODetailComponent;
        let fixture: ComponentFixture<EntityWithServiceClassPaginationAndDTODetailComponent>;
        let service: EntityWithServiceClassPaginationAndDTOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceClassPaginationAndDTODetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceClassPaginationAndDTOService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceClassPaginationAndDTODetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceClassPaginationAndDTODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceClassPaginationAndDTOService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceClassPaginationAndDTO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceClassPaginationAndDTO).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
