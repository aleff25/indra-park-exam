import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Vehicle} from '../../shared/models/Vehicle';
import {isNullOrUndefined} from 'util';
import {Operation} from '../../shared/models/Operation';

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public getData(operation: string, initialDate: Date, finalDate: Date): Observable<Vehicle[]> {
    operation = isNullOrUndefined(operation) ? Operation.NONE : operation;
    const resourceUrl = this.baseUrl + `/parks?operation=${operation}&initialDate=${initialDate}&finalDate=${finalDate}`;
    // @ts-ignore
    return this.http.get(resourceUrl);
  }

  public calculate(plate: string): Observable<number> {
    const resourceUrl = this.baseUrl + `/parks/${plate}/calculate`;
    // @ts-ignore
    return this.http.get(resourceUrl);
  }

  public remove(plate: string) {
    const resourceUrl = this.baseUrl + `/parks?plate=${plate}`;
    // @ts-ignore
    return this.http.delete(resourceUrl);
  }
}
