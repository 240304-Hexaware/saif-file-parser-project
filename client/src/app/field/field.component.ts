import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-field',
  standalone: true,
  imports: [],
  templateUrl: './field.component.html',
  styleUrl: './field.component.css'
})
export class FieldComponent {
    @Input() fieldValue: any;
    @Input() fieldName: string = "";
}
