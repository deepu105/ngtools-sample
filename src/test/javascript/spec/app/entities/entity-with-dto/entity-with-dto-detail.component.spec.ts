import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Jh4TestTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { EntityWithDTODetailComponent } from '../../../../../../main/webapp/app/entities/entity-with-dto/entity-with-dto-detail.component';
import { EntityWithDTOService } from '../../../../../../main/webapp/app/entities/entity-with-dto/entity-with-dto.service';
import { EntityWithDTO } from '../../../../../../main/webapp/app/entities/entity-with-dto/entity-with-dto.model';

describe('Component Tests', () => {

    describe('EntityWithDTO Management Detail Component', () => {
        let comp: EntityWithDTODetailComponent;
        let fixture: ComponentFixture<EntityWithDTODetailComponent>;
        let service: EntityWithDTOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Jh4TestTestModule],
                declarations: [EntityWithDTODetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    EntityWithDTOService,
                    JhiEventManager
                ]
            }).overrideTemplate(EntityWithDTODetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(EntityWithDTODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityWithDTOService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new EntityWithDTO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.entityWithDTO).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
