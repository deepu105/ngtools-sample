import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithDTO } from './entity-with-dto.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithDTOService {

    private resourceUrl = 'api/entity-with-dtos';
    private resourceSearchUrl = 'api/_search/entity-with-dtos';

    constructor(private http: Http) { }

    create(entityWithDTO: EntityWithDTO): Observable<EntityWithDTO> {
        const copy = this.convert(entityWithDTO);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithDTO: EntityWithDTO): Observable<EntityWithDTO> {
        const copy = this.convert(entityWithDTO);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithDTO> {
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

    private convert(entityWithDTO: EntityWithDTO): EntityWithDTO {
        const copy: EntityWithDTO = Object.assign({}, entityWithDTO);
        return copy;
    }
}
