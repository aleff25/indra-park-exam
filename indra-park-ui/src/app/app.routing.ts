import {Routes} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard/dashboard.component';
import {FullComponent} from './full/full.component';
import {FilterComponent} from './filter/filter/filter.component';
import {StoreComponent} from './store/store/store.component';

export const AppRoutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'search',
        component: FilterComponent
      },
      {
        path: 'store',
        component: StoreComponent
      }
    ]
  }
];
