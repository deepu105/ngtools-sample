import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { FieldTestServiceImplEntity } from './field-test-service-impl-entity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FieldTestServiceImplEntityService {

    private resourceUrl = 'api/field-test-service-impl-entities';
    private resourceSearchUrl = 'api/_search/field-test-service-impl-entities';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(fieldTestServiceImplEntity: FieldTestServiceImplEntity): Observable<FieldTestServiceImplEntity> {
        const copy = this.convert(fieldTestServiceImplEntity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(fieldTestServiceImplEntity: FieldTestServiceImplEntity): Observable<FieldTestServiceImplEntity> {
        const copy = this.convert(fieldTestServiceImplEntity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<FieldTestServiceImplEntity> {
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
        entity.localDateMika = this.dateUtils
            .convertLocalDateFromServer(entity.localDateMika);
        entity.localDateRequiredMika = this.dateUtils
            .convertLocalDateFromServer(entity.localDateRequiredMika);
        entity.instantMika = this.dateUtils
            .convertDateTimeFromServer(entity.instantMika);
        entity.instanteRequiredMika = this.dateUtils
            .convertDateTimeFromServer(entity.instanteRequiredMika);
        entity.zonedDateTimeMika = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeMika);
        entity.zonedDateTimeRequiredMika = this.dateUtils
            .convertDateTimeFromServer(entity.zonedDateTimeRequiredMika);
    }

    private convert(fieldTestServiceImplEntity: FieldTestServiceImplEntity): FieldTestServiceImplEntity {
        const copy: FieldTestServiceImplEntity = Object.assign({}, fieldTestServiceImplEntity);
        copy.localDateMika = this.dateUtils
            .convertLocalDateToServer(fieldTestServiceImplEntity.localDateMika);
        copy.localDateRequiredMika = this.dateUtils
            .convertLocalDateToServer(fieldTestServiceImplEntity.localDateRequiredMika);

        copy.instantMika = this.dateUtils.toDate(fieldTestServiceImplEntity.instantMika);

        copy.instanteRequiredMika = this.dateUtils.toDate(fieldTestServiceImplEntity.instanteRequiredMika);

        copy.zonedDateTimeMika = this.dateUtils.toDate(fieldTestServiceImplEntity.zonedDateTimeMika);

        copy.zonedDateTimeRequiredMika = this.dateUtils.toDate(fieldTestServiceImplEntity.zonedDateTimeRequiredMika);
        return copy;
    }
}
