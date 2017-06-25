import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestServiceClassEntity } from './field-test-service-class-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestServiceClassEntityService {

    private resourceUrl = 'api/field-test-service-class-entities';
    private resourceSearchUrl = 'api/_search/field-test-service-class-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestServiceClassEntity: FieldTestServiceClassEntity): Observable<FieldTestServiceClassEntity> {
        const copy = this.convert(fieldTestServiceClassEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestServiceClassEntity: FieldTestServiceClassEntity): Observable<FieldTestServiceClassEntity> {
        const copy = this.convert(fieldTestServiceClassEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestServiceClassEntity> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.localDateBob = this.dateUtils
            .convertLocalDateFromServer(entity.localDateBob);
        entity.localDateRequiredBob = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredBob);
        entity.instantBob = this.dateUtils
            .convertDateTimeFromServer(entity.instantBob);
        entity.instanteRequiredBob = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredBob);
        entity.zonedDateTimeBob = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeBob);
        entity.zonedDateTimeRequiredBob = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredBob);
    }

    private convert(fieldTestServiceClassEntity: FieldTestServiceClassEntity): FieldTestServiceClassEntity {
        const copy: FieldTestServiceClassEntity = Object.assign({}, fieldTestServiceClassEntity);
        copy.localDateBob = this.dateUtils
            .convertLocalDateToServer(fieldTestServiceClassEntity.localDateBob);
        copy.localDateRequiredBob = this.dateUtils
            .convertLocalDateToServer(fieldTestServiceClassEntity.localDateRequiredBob);

        copy.instantBob = this.dateUtils.toDate(fieldTestServiceClassEntity.instantBob);

        copy.instanteRequiredBob = this.dateUtils.toDate(fieldTestServiceClassEntity.instanteRequiredBob);

        copy.zonedDateTimeBob = this.dateUtils.toDate(fieldTestServiceClassEntity.zonedDateTimeBob);

        copy.zonedDateTimeRequiredBob = this.dateUtils.toDate(fieldTestServiceClassEntity.zonedDateTimeRequiredBob);
        return copy;
    }
}
