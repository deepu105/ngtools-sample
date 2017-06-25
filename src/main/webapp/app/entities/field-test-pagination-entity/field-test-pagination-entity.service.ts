import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestPaginationEntity } from './field-test-pagination-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestPaginationEntityService {

    private resourceUrl = 'api/field-test-pagination-entities';
    private resourceSearchUrl = 'api/_search/field-test-pagination-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestPaginationEntity: FieldTestPaginationEntity): Observable<FieldTestPaginationEntity> {
        const copy = this.convert(fieldTestPaginationEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestPaginationEntity: FieldTestPaginationEntity): Observable<FieldTestPaginationEntity> {
        const copy = this.convert(fieldTestPaginationEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestPaginationEntity> {
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
        entity.localDateAlice = this.dateUtils
            .convertLocalDateFromServer(entity.localDateAlice);
        entity.localDateRequiredAlice = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredAlice);
        entity.instantAlice = this.dateUtils
            .convertDateTimeFromServer(entity.instantAlice);
        entity.instanteRequiredAlice = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredAlice);
        entity.zonedDateTimeAlice = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeAlice);
        entity.zonedDateTimeRequiredAlice = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredAlice);
    }

    private convert(fieldTestPaginationEntity: FieldTestPaginationEntity): FieldTestPaginationEntity {
        const copy: FieldTestPaginationEntity = Object.assign({}, fieldTestPaginationEntity);
        copy.localDateAlice = this.dateUtils
            .convertLocalDateToServer(fieldTestPaginationEntity.localDateAlice);
        copy.localDateRequiredAlice = this.dateUtils
            .convertLocalDateToServer(fieldTestPaginationEntity.localDateRequiredAlice);

        copy.instantAlice = this.dateUtils.toDate(fieldTestPaginationEntity.instantAlice);

        copy.instanteRequiredAlice = this.dateUtils.toDate(fieldTestPaginationEntity.instanteRequiredAlice);

        copy.zonedDateTimeAlice = this.dateUtils.toDate(fieldTestPaginationEntity.zonedDateTimeAlice);

        copy.zonedDateTimeRequiredAlice = this.dateUtils.toDate(fieldTestPaginationEntity.zonedDateTimeRequiredAlice);
        return copy;
    }
}
