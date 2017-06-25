import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithPaginationAndDTO } from './entity-with-pagination-and-dto.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithPaginationAndDTOService {

    private resourceUrl = 'api/entity-with-pagination-and-dtos';
    private resourceSearchUrl = 'api/_search/entity-with-pagination-and-dtos';

    constructor(private http: Http) { }

    create(entityWithPaginationAndDTO: EntityWithPaginationAndDTO): Observable<EntityWithPaginationAndDTO> {
        const copy = this.convert(entityWithPaginationAndDTO);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithPaginationAndDTO: EntityWithPaginationAndDTO): Observable<EntityWithPaginationAndDTO> {
        const copy = this.convert(entityWithPaginationAndDTO);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithPaginationAndDTO> {
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

    private convert(entityWithPaginationAndDTO: EntityWithPaginationAndDTO): EntityWithPaginationAndDTO {
        const copy: EntityWithPaginationAndDTO = Object.assign({}, entityWithPaginationAndDTO);
        return copy;
    }
}
