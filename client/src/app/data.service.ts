import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  // Not using these at the moment
  recordData: any;
  specData: any;
  fileData: any;

  constructor(private http: HttpClient, @Inject(PLATFORM_ID) private platformId: Object) { 

  }

  // Get the records of the user
  getAllRecords(): Observable<any> {
    let url: string = "";
    if (isPlatformBrowser(this.platformId)) {
      url = "http://localhost:8080/users/" + sessionStorage.getItem("currentUser") as string + "/records";
    }

    return this.http.get<any>(url);
  }

  // Get the specifications of the user's records
  getAllSpecs(): Observable<any> {
    let url: string = "";
    if (isPlatformBrowser(this.platformId)) {
      url = "http://localhost:8080/users/" + sessionStorage.getItem("currentUser") as string + "/specifications";
    }

    return this.http.get<any>(url);
  }

  // Get the file metadata of all the user's flat-files
  getAllFileMetadata(): Observable<any> {
    let url: string = "";
    if (isPlatformBrowser(this.platformId)) {
      url = "http://localhost:8080/users/" + sessionStorage.getItem("currentUser") as string + "/flat-files";
    }

    return this.http.get<any>(url);
  }

}
