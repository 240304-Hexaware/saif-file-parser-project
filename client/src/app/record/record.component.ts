import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FieldComponent } from '../field/field.component';

@Component({
  selector: 'app-record',
  standalone: true,
  imports: [
    CommonModule,
    FieldComponent
  ],
  templateUrl: './record.component.html',
  styleUrl: './record.component.css'
})
export class RecordComponent {

  @Input() record: Record = {};

  constructor(){
  }

  getKeys(){
    return Object.keys(this.record);
  }

}

export interface Record {
  [key: string]: any;
}
