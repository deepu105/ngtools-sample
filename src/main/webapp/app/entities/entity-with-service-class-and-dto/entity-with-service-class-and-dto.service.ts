import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { EntityWithServiceClassAndDTO } from './entity-with-service-class-and-dto.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EntityWithServiceClassAndDTOService {

    private resourceUrl = 'api/entity-with-service-class-and-dtos';
    private resourceSearchUrl = 'api/_search/entity-with-service-class-and-dtos';

    constructor(private http: Http) { }

    create(entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO): Observable<EntityWithServiceClassAndDTO> {
        const copy = this.convert(entityWithServiceClassAndDTO);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO): Observable<EntityWithServiceClassAndDTO> {
        const copy = this.convert(entityWithServiceClassAndDTO);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<EntityWithServiceClassAndDTO> {
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

    private convert(entityWithServiceClassAndDTO: EntityWithServiceClassAndDTO): EntityWithServiceClassAndDTO {
        const copy: EntityWithServiceClassAndDTO = Object.assign({}, entityWithServiceClassAndDTO);
        return copy;
    }
}
