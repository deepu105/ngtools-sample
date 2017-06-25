import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestMapstructEntity } from './field-test-mapstruct-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestMapstructEntityService {

    private resourceUrl = 'api/field-test-mapstruct-entities';
    private resourceSearchUrl = 'api/_search/field-test-mapstruct-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestMapstructEntity: FieldTestMapstructEntity): Observable<FieldTestMapstructEntity> {
        const copy = this.convert(fieldTestMapstructEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestMapstructEntity: FieldTestMapstructEntity): Observable<FieldTestMapstructEntity> {
        const copy = this.convert(fieldTestMapstructEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestMapstructEntity> {
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
        entity.localDateEva = this.dateUtils
            .convertLocalDateFromServer(entity.localDateEva);
        entity.localDateRequiredEva = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredEva);
        entity.instantEva = this.dateUtils
            .convertDateTimeFromServer(entity.instantEva);
        entity.instanteRequiredEva = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredEva);
        entity.zonedDateTimeEva = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeEva);
        entity.zonedDateTimeRequiredEva = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredEva);
    }

    private convert(fieldTestMapstructEntity: FieldTestMapstructEntity): FieldTestMapstructEntity {
        const copy: FieldTestMapstructEntity = Object.assign({}, fieldTestMapstructEntity);
        copy.localDateEva = this.dateUtils
            .convertLocalDateToServer(fieldTestMapstructEntity.localDateEva);
        copy.localDateRequiredEva = this.dateUtils
            .convertLocalDateToServer(fieldTestMapstructEntity.localDateRequiredEva);

        copy.instantEva = this.dateUtils.toDate(fieldTestMapstructEntity.instantEva);

        copy.instanteRequiredEva = this.dateUtils.toDate(fieldTestMapstructEntity.instanteRequiredEva);

        copy.zonedDateTimeEva = this.dateUtils.toDate(fieldTestMapstructEntity.zonedDateTimeEva);

        copy.zonedDateTimeRequiredEva = this.dateUtils.toDate(fieldTestMapstructEntity.zonedDateTimeRequiredEva);
        return copy;
    }
}
