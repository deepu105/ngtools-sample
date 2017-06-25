import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithServiceClassPaginationAndDTO } from './entity-with-service-class-pagination-and-dto.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithServiceClassPaginationAndDTOService {

    private resourceUrl = 'api/entity-with-service-class-pagination-and-dtos';
    private resourceSearchUrl = 'api/_search/entity-with-service-class-pagination-and-dtos';

    constructor(private http: Http) { }

    create(entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO):
        Observable<EntityWithServiceClassPaginationAndDTO> {
        const copy = this.convert(entityWithServiceClassPaginationAndDTO);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO):
        Observable<EntityWithServiceClassPaginationAndDTO> {
        const copy = this.convert(entityWithServiceClassPaginationAndDTO);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithServiceClassPaginationAndDTO> {
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

    private convert(entityWithServiceClassPaginationAndDTO: EntityWithServiceClassPaginationAndDTO): EntityWithServiceClassPaginationAndDTO {
        const copy: EntityWithServiceClassPaginationAndDTO = Object.assign({}, entityWithServiceClassPaginationAndDTO);
        return copy;
    }
}
