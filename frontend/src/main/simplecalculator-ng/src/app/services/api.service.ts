import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

    constructor(
        private http: HttpClient
    ) { }

    private doRequest(method: string, urlSegment = '') {
        const endpointUrl = this.getEndpointUrl();
        const url = `${endpointUrl}${method}/${urlSegment}`;
        return this.http.get(url);
    }

    public solveEquation(number1: number, number2: number, signSymbol: string) {
        const method = 'solve';
        const urlSegment = `${number1}/${signSymbol}/${number2}`;
        return this.doRequest(method, urlSegment);
    }

    private getEndpointUrl() {
        return environment.endpointUrl;
    }

}
