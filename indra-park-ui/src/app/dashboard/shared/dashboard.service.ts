import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }


  public dasboardData(): Observable<any> {
    const startOfWeek = moment().startOf('week').toDate();
    const endOfWeek = moment().endOf('week').toDate();

    const resourceUrl = this.baseUrl + `/parks/dashboard?initialDate=${startOfWeek}&finalDate=${endOfWeek}`;
    return this.http.get(resourceUrl);
  }

}
