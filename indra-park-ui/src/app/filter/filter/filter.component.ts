import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {FilterService} from '../shared/filter.service';
import {Vehicle} from '../../shared/models/Vehicle';
import {take} from 'rxjs/operators';
import {Operation} from '../../shared/models/Operation';
import {DialogComponent} from '../../shared/components/dialog/dialog.component';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  initialDate = new FormControl(new Date());
  finalDate = new FormControl(new Date());
  operation: Operation = Operation.NONE;

  displayedColumns: string[] = ['plate', 'model', 'operation', 'updatedAt', 'star'];
  dataSource: MatTableDataSource<any>;

  constructor(private filterService: FilterService, public dialog: MatDialog, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.search();
  }

  protected search() {
    this.filterService.getData(this.operation, this.initialDate.value, this.finalDate.value)
      .pipe(
        take(1)
      )
      .subscribe((vehicles: Vehicle[]) => {
        this.dataSource = new MatTableDataSource(vehicles);
      });
  }

  protected leave(vehicle: Vehicle) {
    this.filterService.calculate(vehicle.plate)
      .pipe(
        take(1)
      )
      .subscribe(res => this.openDialog(res, vehicle));

  }

  openDialog(payment: number, vehicle: Vehicle): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '400px',
      data: {payment, vehicle}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.dataSource = new MatTableDataSource();
      this.search();
    });
  }


  protected remove(vehicle: Vehicle) {
    this.filterService.remove(vehicle.plate)
      .pipe(
        take(1)
      )
      .subscribe(res => {
        const message = 'Vehicle succesfully removed';
        this.snackBar.open(message, 'OK', {
          duration: 3000
        });
      }, error => this.snackBar.open(error, 'OK', {
        duration: 3000
      }));
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
