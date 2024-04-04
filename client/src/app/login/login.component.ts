import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username: string;
  password: string;

  constructor(private http: HttpClient, private router: Router) {
    this.username = "";
    this.password = "";
  }

  onLogin(): void {
    const credentials = { username: this.username, password: this.password };
    const url = "http://localhost:8080/login";
    
    this.http.post<any>(url, credentials)
      .subscribe({
        next: (response) => {
          //store username in session to send it as headers on subsequent requests
          sessionStorage.setItem("currentUser", response.username); 
          this.router.navigateByUrl("dashboard");
        },
        error: () => {
          alert("Invalid username and/or password.");
        }
      });
  }

  onRegister(): void {
    this.router.navigateByUrl("registration");
  }

}
