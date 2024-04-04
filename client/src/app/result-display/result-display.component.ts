import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-result-display',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './result-display.component.html',
  styleUrl: './result-display.component.css'
})
export class ResultDisplayComponent {
  @Input() result: any;
   numRecords: number;

  constructor() {
    this.result = null;
    this.numRecords = 0;
  }

  ngOnInit(): void {
    this.numRecords = this.result.length;
  }
}
