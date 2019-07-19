import {Component} from '@angular/core';

@Component({
  selector: 'snack-bar-component',
  templateUrl: './custom-snack-bar.component.html',
  styles: [`
    .notification-snackbar {
      color: hotpink;
    }
  `],
})
export class CustomSnackBarComponent {
}
