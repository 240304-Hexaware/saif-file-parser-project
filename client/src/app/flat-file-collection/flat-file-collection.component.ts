import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { DataService } from '../data.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-flat-file-collection',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './flat-file-collection.component.html',
  styleUrl: './flat-file-collection.component.css'
})
export class FlatFileCollectionComponent {
    data: any;

    constructor(private dataService: DataService){
    }

    ngOnInit(): void {
      this.initializeData();
    }
  
    initializeData(): void {
      this.dataService.getAllFileMetadata().subscribe({
        next: (response) => {
          this.data = response;
        },
        error: () => {
          console.log("Error getting file metadata.");
        }
      });
    }

}
