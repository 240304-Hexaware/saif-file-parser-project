import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Router, RouterOutlet, RouterLink, RouterLinkActive, RouterModule} from '@angular/router';
import { ParserComponent } from "../parser/parser.component";
import { FlatFileCollectionComponent } from '../flat-file-collection/flat-file-collection.component';
import { SpecFileCollectionComponent } from '../spec-file-collection/spec-file-collection.component';
import { RecordsListComponent } from '../records-list/records-list.component';
import { DataService } from '../data.service';


@Component({
    selector: 'app-dashboard',
    standalone: true,
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css',
    imports: [
      RouterModule,
      RouterOutlet,
      RouterLink, 
      RouterLinkActive,
      CommonModule, 
      ParserComponent,
      FlatFileCollectionComponent,
      SpecFileCollectionComponent,
      RecordsListComponent
    ]
})
export class DashboardComponent {
  user: string = "";

  constructor(private router: Router, @Inject(PLATFORM_ID) private platformId: Object, private dataService: DataService) { 
    
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.user = sessionStorage.getItem("currentUser") as string;
    }
  }

  onLogout(): void {
    sessionStorage.removeItem("currentUser");
    this.router.navigateByUrl("/login");
  }

}