import { CommonModule} from '@angular/common';
import { Component } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-spec-file-collection',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './spec-file-collection.component.html',
  styleUrl: './spec-file-collection.component.css'
})
export class SpecFileCollectionComponent {
    data: any;

    constructor(private dataService: DataService) {
    }

    ngOnInit(): void {
      this.initializeData();
    }
  
    initializeData(): void {
      this.dataService.getAllSpecs().subscribe({
        next: (response) => {
          this.data = response;
        },
        error: () => {
          console.log("Error getting specifications.");
        }
      });
    }
}
