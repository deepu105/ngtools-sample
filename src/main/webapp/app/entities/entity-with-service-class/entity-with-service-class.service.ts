import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithServiceClass } from './entity-with-service-class.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithServiceClassService {

    private resourceUrl = 'api/entity-with-service-classes';
    private resourceSearchUrl = 'api/_search/entity-with-service-classes';

    constructor(private http: Http) { }

    create(entityWithServiceClass: EntityWithServiceClass): Observable<EntityWithServiceClass> {
        const copy = this.convert(entityWithServiceClass);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithServiceClass: EntityWithServiceClass): Observable<EntityWithServiceClass> {
        const copy = this.convert(entityWithServiceClass);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithServiceClass> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
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
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(entityWithServiceClass: EntityWithServiceClass): EntityWithServiceClass {
        const copy: EntityWithServiceClass = Object.assign({}, entityWithServiceClass);
        return copy;
    }
}
