import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestInfiniteScrollEntity } from './field-test-infinite-scroll-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestInfiniteScrollEntityService {

    private resourceUrl = 'api/field-test-infinite-scroll-entities';
    private resourceSearchUrl = 'api/_search/field-test-infinite-scroll-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity): Observable<FieldTestInfiniteScrollEntity> {
        const copy = this.convert(fieldTestInfiniteScrollEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity): Observable<FieldTestInfiniteScrollEntity> {
        const copy = this.convert(fieldTestInfiniteScrollEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestInfiniteScrollEntity> {
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
        entity.localDateHugo = this.dateUtils
            .convertLocalDateFromServer(entity.localDateHugo);
        entity.localDateRequiredHugo = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredHugo);
        entity.instantHugo = this.dateUtils
            .convertDateTimeFromServer(entity.instantHugo);
        entity.instanteRequiredHugo = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredHugo);
        entity.zonedDateTimeHugo = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeHugo);
        entity.zonedDateTimeRequiredHugo = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredHugo);
    }

    private convert(fieldTestInfiniteScrollEntity: FieldTestInfiniteScrollEntity): FieldTestInfiniteScrollEntity {
        const copy: FieldTestInfiniteScrollEntity = Object.assign({}, fieldTestInfiniteScrollEntity);
        copy.localDateHugo = this.dateUtils
            .convertLocalDateToServer(fieldTestInfiniteScrollEntity.localDateHugo);
        copy.localDateRequiredHugo = this.dateUtils
            .convertLocalDateToServer(fieldTestInfiniteScrollEntity.localDateRequiredHugo);

        copy.instantHugo = this.dateUtils.toDate(fieldTestInfiniteScrollEntity.instantHugo);

        copy.instanteRequiredHugo = this.dateUtils.toDate(fieldTestInfiniteScrollEntity.instanteRequiredHugo);

        copy.zonedDateTimeHugo = this.dateUtils.toDate(fieldTestInfiniteScrollEntity.zonedDateTimeHugo);

        copy.zonedDateTimeRequiredHugo = this.dateUtils.toDate(fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo);
        return copy;
    }
}
