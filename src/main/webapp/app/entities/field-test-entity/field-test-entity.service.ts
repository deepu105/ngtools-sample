import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestEntity } from './field-test-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestEntityService {

    private resourceUrl = 'api/field-test-entities';
    private resourceSearchUrl = 'api/_search/field-test-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestEntity: FieldTestEntity): Observable<FieldTestEntity> {
        const copy = this.convert(fieldTestEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestEntity: FieldTestEntity): Observable<FieldTestEntity> {
        const copy = this.convert(fieldTestEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestEntity> {
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
        entity.localDateTom = this.dateUtils
            .convertLocalDateFromServer(entity.localDateTom);
        entity.localDateRequiredTom = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredTom);
        entity.instantTom = this.dateUtils
            .convertDateTimeFromServer(entity.instantTom);
        entity.instanteRequiredTom = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredTom);
        entity.zonedDateTimeTom = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeTom);
        entity.zonedDateTimeRequiredTom = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredTom);
    }

    private convert(fieldTestEntity: FieldTestEntity): FieldTestEntity {
        const copy: FieldTestEntity = Object.assign({}, fieldTestEntity);
        copy.localDateTom = this.dateUtils
            .convertLocalDateToServer(fieldTestEntity.localDateTom);
        copy.localDateRequiredTom = this.dateUtils
            .convertLocalDateToServer(fieldTestEntity.localDateRequiredTom);

        copy.instantTom = this.dateUtils.toDate(fieldTestEntity.instantTom);

        copy.instanteRequiredTom = this.dateUtils.toDate(fieldTestEntity.instanteRequiredTom);

        copy.zonedDateTimeTom = this.dateUtils.toDate(fieldTestEntity.zonedDateTimeTom);

        copy.zonedDateTimeRequiredTom = this.dateUtils.toDate(fieldTestEntity.zonedDateTimeRequiredTom);
        return copy;
    }
}
