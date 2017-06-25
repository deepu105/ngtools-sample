import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithPagination } from './entity-with-pagination.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithPaginationService {

    private resourceUrl = 'api/entity-with-paginations';
    private resourceSearchUrl = 'api/_search/entity-with-paginations';

    constructor(private http: Http) { }

    create(entityWithPagination: EntityWithPagination): Observable<EntityWithPagination> {
        const copy = this.convert(entityWithPagination);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithPagination: EntityWithPagination): Observable<EntityWithPagination> {
        const copy = this.convert(entityWithPagination);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithPagination> {
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

    private convert(entityWithPagination: EntityWithPagination): EntityWithPagination {
        const copy: EntityWithPagination = Object.assign({}, entityWithPagination);
        return copy;
    }
}
