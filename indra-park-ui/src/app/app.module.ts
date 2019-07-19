import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FullComponent} from './full/full.component';
import {AppRoutes} from './app.routing';
import {RouterModule} from '@angular/router';
import {MatMenuModule} from '@angular/material';
import {ChartsModule} from 'ng2-charts';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ParkMaterialModule} from './material-module';
import {HttpClientModule} from '@angular/common/http';
import {DashboardComponent} from './dashboard/dashboard/dashboard.component';
import {StoreComponent} from './store/store/store.component';
import {FilterComponent} from './filter/filter/filter.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DialogComponent} from './shared/components/dialog/dialog.component';
import {CustomSnackBarComponent} from './shared/components/snackbar/custom-snack-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    DashboardComponent,
    StoreComponent,
    FilterComponent,
    DialogComponent,
    CustomSnackBarComponent
  ],
  imports: [
    MatMenuModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    ParkMaterialModule,
    BrowserAnimationsModule,
    ChartsModule,
    RouterModule.forRoot(AppRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    DialogComponent,
    CustomSnackBarComponent
  ]
})
export class AppModule {
}
