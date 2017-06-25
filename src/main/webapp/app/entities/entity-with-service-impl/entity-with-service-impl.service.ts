import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithServiceImpl } from './entity-with-service-impl.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithServiceImplService {

    private resourceUrl = 'api/entity-with-service-impls';
    private resourceSearchUrl = 'api/_search/entity-with-service-impls';

    constructor(private http: Http) { }

    create(entityWithServiceImpl: EntityWithServiceImpl): Observable<EntityWithServiceImpl> {
        const copy = this.convert(entityWithServiceImpl);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithServiceImpl: EntityWithServiceImpl): Observable<EntityWithServiceImpl> {
        const copy = this.convert(entityWithServiceImpl);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithServiceImpl> {
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

    private convert(entityWithServiceImpl: EntityWithServiceImpl): EntityWithServiceImpl {
        const copy: EntityWithServiceImpl = Object.assign({}, entityWithServiceImpl);
        return copy;
    }
}
