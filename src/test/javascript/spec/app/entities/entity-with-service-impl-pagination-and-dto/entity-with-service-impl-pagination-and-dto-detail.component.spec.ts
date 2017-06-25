import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithServiceImplPaginationAndDTODetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-pagination-and-dto/entity-with-service-impl-pagination-and-dto-detail.component';
import { EntityWithServiceImplPaginationAndDTOService } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-pagination-and-dto/entity-with-service-impl-pagination-and-dto.service';
import { EntityWithServiceImplPaginationAndDTO } from '../../../../../../main/webapp/app/entities/entity-with-service-impl-pagination-and-dto/entity-with-service-impl-pagination-and-dto.model';

describe('Component Tests', () => {

    describe('EntityWithServiceImplPaginationAndDTO Management Detail Component', () => {
        let comp: EntityWithServiceImplPaginationAndDTODetailComponent;
        let fixture: ComponentFixture<EntityWithServiceImplPaginationAndDTODetailComponent>;
        let service: EntityWithServiceImplPaginationAndDTOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithServiceImplPaginationAndDTODetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithServiceImplPaginationAndDTOService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithServiceImplPaginationAndDTODetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithServiceImplPaginationAndDTODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithServiceImplPaginationAndDTOService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithServiceImplPaginationAndDTO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithServiceImplPaginationAndDTO).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
