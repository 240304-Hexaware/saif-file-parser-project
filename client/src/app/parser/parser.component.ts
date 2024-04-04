import { Component } from '@angular/core';
import { ResultDisplayComponent } from "../result-display/result-display.component";
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { DataService } from '../data.service';

@Component({
    selector: 'app-parser',
    standalone: true,
    templateUrl: './parser.component.html',
    styleUrl: './parser.component.css',
    imports: [
      CommonModule,
      ResultDisplayComponent,
      HttpClientModule
    ]
})

export class ParserComponent {
  flatfile: File | null;
  specfile: File | null;
  parsedData: any;
  resultsProduced: boolean;

  constructor(private http: HttpClient, private recordDataService: DataService) {
    this.flatfile = null;
    this.specfile = null;
    this.parsedData = null;
    this.resultsProduced = false;
  }

  parse(): void {
    if (!this.specfile || !this.flatfile) {
      return;
    }

    const url = "http://localhost:8080/files"
    const formData = new FormData();
    formData.append('specificationFile', this.specfile);
    formData.append('recordFile', this.flatfile);

    const options: Object = {
      observe: "body",
      contentType: "text",
      accept: "application/json",
      headers: new HttpHeaders({
        currentUser: sessionStorage.getItem("currentUser") as string
      })
    };

    this.http.post<any[]>(url, formData, options)
      .subscribe({
        next: (response) => {
        this.parsedData = response; 
        this.resultsProduced = true;
        this.recordDataService.recordData = this.parsedData;
      },
      error: () => {
        console.log('Error parsing files!');
      }
    });

  }

  onRecordFileSelect(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files) {
      this.flatfile = inputElement.files[0];
    }
  }

  onSpecSelect(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files) {
      this.specfile = inputElement.files[0];
    }
  }  

}
