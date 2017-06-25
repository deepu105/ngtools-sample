import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestPagerEntity } from './field-test-pager-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestPagerEntityService {

    private resourceUrl = 'api/field-test-pager-entities';
    private resourceSearchUrl = 'api/_search/field-test-pager-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestPagerEntity: FieldTestPagerEntity): Observable<FieldTestPagerEntity> {
        const copy = this.convert(fieldTestPagerEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestPagerEntity: FieldTestPagerEntity): Observable<FieldTestPagerEntity> {
        const copy = this.convert(fieldTestPagerEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestPagerEntity> {
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
        entity.localDateJade = this.dateUtils
            .convertLocalDateFromServer(entity.localDateJade);
        entity.localDateRequiredJade = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredJade);
        entity.instantJade = this.dateUtils
            .convertDateTimeFromServer(entity.instantJade);
        entity.instanteRequiredJade = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredJade);
        entity.zonedDateTimeJade = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeJade);
        entity.zonedDateTimeRequiredJade = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredJade);
    }

    private convert(fieldTestPagerEntity: FieldTestPagerEntity): FieldTestPagerEntity {
        const copy: FieldTestPagerEntity = Object.assign({}, fieldTestPagerEntity);
        copy.localDateJade = this.dateUtils
            .convertLocalDateToServer(fieldTestPagerEntity.localDateJade);
        copy.localDateRequiredJade = this.dateUtils
            .convertLocalDateToServer(fieldTestPagerEntity.localDateRequiredJade);

        copy.instantJade = this.dateUtils.toDate(fieldTestPagerEntity.instantJade);

        copy.instanteRequiredJade = this.dateUtils.toDate(fieldTestPagerEntity.instanteRequiredJade);

        copy.zonedDateTimeJade = this.dateUtils.toDate(fieldTestPagerEntity.zonedDateTimeJade);

        copy.zonedDateTimeRequiredJade = this.dateUtils.toDate(fieldTestPagerEntity.zonedDateTimeRequiredJade);
        return copy;
    }
}
