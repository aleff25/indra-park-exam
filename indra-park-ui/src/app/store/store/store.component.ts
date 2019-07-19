import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StoreService} from '../shared/store.service';
import {VehicleBuilder} from '../../shared/models/Vehicle';
import {MatSnackBar} from '@angular/material';
import {CustomSnackBarComponent} from '../../shared/components/snackbar/custom-snack-bar.component';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  forms: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private storeService: StoreService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.forms = this.formBuilder.group({
      plate: [null, [Validators.pattern('[A-Z]{3}-[0-9]{4}'), Validators.required, Validators.maxLength(9)]],
      model: [null, Validators.required],
    });
  }

  onSubmit() {
    const values = this.forms.value;
    const builder = new VehicleBuilder(values.plate, values.model);
    this.storeService.storeVehicle(builder).subscribe(res => {
      this.openSnackBar();
      this.forms.reset();
      this.forms.clearValidators();
    }, (err) => {
      this.snackBar.open(err, 'OK', {
        duration: 3000
      });
    });

  }

  openSnackBar() {
    this.snackBar.openFromComponent(CustomSnackBarComponent, {
      duration: 5000,
    });
  }
}


