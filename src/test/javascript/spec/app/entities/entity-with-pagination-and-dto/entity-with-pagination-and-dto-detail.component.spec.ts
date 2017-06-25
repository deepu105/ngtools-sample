import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithPaginationAndDTODetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-pagination-and-dto/entity-with-pagination-and-dto-detail.component';
import { EntityWithPaginationAndDTOService } from '../../../../../../main/webapp/app/entities/entity-with-pagination-and-dto/entity-with-pagination-and-dto.service';
import { EntityWithPaginationAndDTO } from '../../../../../../main/webapp/app/entities/entity-with-pagination-and-dto/entity-with-pagination-and-dto.model';

describe('Component Tests', () => {

    describe('EntityWithPaginationAndDTO Management Detail Component', () => {
        let comp: EntityWithPaginationAndDTODetailComponent;
        let fixture: ComponentFixture<EntityWithPaginationAndDTODetailComponent>;
        let service: EntityWithPaginationAndDTOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithPaginationAndDTODetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithPaginationAndDTOService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithPaginationAndDTODetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithPaginationAndDTODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithPaginationAndDTOService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithPaginationAndDTO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithPaginationAndDTO).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
