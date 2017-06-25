import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithServiceImplAndPagination } from './entity-with-service-impl-and-pagination.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithServiceImplAndPaginationService {

    private resourceUrl = 'api/entity-with-service-impl-and-paginations';
    private resourceSearchUrl = 'api/_search/entity-with-service-impl-and-paginations';

    constructor(private http: Http) { }

    create(entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination):
        Observable<EntityWithServiceImplAndPagination> {
        const copy = this.convert(entityWithServiceImplAndPagination);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination):
        Observable<EntityWithServiceImplAndPagination> {
        const copy = this.convert(entityWithServiceImplAndPagination);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithServiceImplAndPagination> {
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

    private convert(entityWithServiceImplAndPagination: EntityWithServiceImplAndPagination): EntityWithServiceImplAndPagination {
        const copy: EntityWithServiceImplAndPagination = Object.assign({}, entityWithServiceImplAndPagination);
        return copy;
    }
}
