import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiService } from './api.service';

describe('ApiService', () => {

    beforeEach(() => TestBed.configureTestingModule({
        imports: [HttpClientTestingModule], 
        providers: [ApiService]
    }));

    it('should be created', () => {
     const service: ApiService = TestBed.get(ApiService);
     expect(service).toBeTruthy();
    });

    it('should have solveEquation function', () => {
     const service: ApiService = TestBed.get(ApiService);
     expect(service.solveEquation).toBeTruthy();
    });

    it('should have getEquationsHistory function', () => {
     const service: ApiService = TestBed.get(ApiService);
     expect(service.getEquationsHistory).toBeTruthy();
    });

    it('', () => {
        const tkDataService = getTestBed().get(TkDataService)

        httpService.get<any>(dummyUrl, dummyTelemetryAction).subscribe(
                    (response: HttpResponse<any>) => {
                        expect(response).toBeTruthy();
        }

        const req1 = httpMock.expectOne(dummyUrl);

        req1.flush(null, { status: 200, statusText: 'ok' });
    });

});
