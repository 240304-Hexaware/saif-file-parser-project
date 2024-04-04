import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { RecordComponent } from '../record/record.component';
import { DataService } from '../data.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-records-list',
  standalone: true,
  imports: [
      CommonModule,
      RecordComponent,
      HttpClientModule,
      FormsModule
    ],
  templateUrl: './records-list.component.html',
  styleUrl: './records-list.component.css'
})
export class RecordsListComponent {
  data: any;
  selectedSpec: string;
  selections: Set<string>;

  constructor(private dataService: DataService, 
              private http: HttpClient,
              @Inject(PLATFORM_ID) private platformId: Object){
    this.selectedSpec = "";
    this.selections = new Set();   
  }

  ngOnInit(): void {
    this.initializeData();
    this.setSelections();
  }

  initializeData(): void {
    this.dataService.getAllRecords().subscribe({
      next: (response) => {
        this.data = response;
      },
      error: () => {
        console.log("Error getting records:");
      }
    });
  }

  setSelections(): void {
    this.dataService.getAllSpecs().subscribe({
      next: (response) => {
        response.forEach((spec: { specFileName: string }) => {
          this.selections.add(spec.specFileName);
        });
      },
      error: () => {
        console.log("Error getting specifications.");
      }
    });
  }

  showAllRecordsOfUser(): void {
    this.initializeData();
  }

  showSpecificRecords(): void {
    let url: string = "";
    if (isPlatformBrowser(this.platformId)) {
      url = "http://localhost:8080/users/" + sessionStorage.getItem("currentUser") as string + "/specifications/" +
             this.selectedSpec + "/records";
    }
  
    this.http.get<any>(url)
      .subscribe({
          next: (response) => {
          this.data = response; 
        },
        error: () => {
          console.log("Error getting records of the user by specification.");
        }
      });
  }

}
