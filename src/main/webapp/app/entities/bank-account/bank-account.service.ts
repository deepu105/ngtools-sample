import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { BankAccount } from './bank-account.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class BankAccountService {

    private resourceUrl = 'api/bank-accounts';
    private resourceSearchUrl = 'api/_search/bank-accounts';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(bankAccount: BankAccount): Observable<BankAccount> {
        const copy = this.convert(bankAccount);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(bankAccount: BankAccount): Observable<BankAccount> {
        const copy = this.convert(bankAccount);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<BankAccount> {
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
        entity.openingDay = this.dateUtils
            .convertLocalDateFromServer(entity.openingDay);
        entity.lastOperationDate = this.dateUtils
            .convertDateTimeFromServer(entity.lastOperationDate);
    }

    private convert(bankAccount: BankAccount): BankAccount {
        const copy: BankAccount = Object.assign({}, bankAccount);
        copy.openingDay = this.dateUtils
            .convertLocalDateToServer(bankAccount.openingDay);

        copy.lastOperationDate = this.dateUtils.toDate(bankAccount.lastOperationDate);
        return copy;
    }
}
