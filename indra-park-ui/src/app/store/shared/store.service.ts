import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Vehicle} from '../../shared/models/Vehicle';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public storeVehicle(vehicle: Vehicle): Observable<Vehicle> {
    const resourceUrl = this.baseUrl + `/parks`;
    // @ts-ignore
    return this.http.post(resourceUrl, vehicle);
  }
}
