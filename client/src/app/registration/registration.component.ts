import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  
  username: string;
  password: string;
  repeatedPassword: string;

  constructor(private http: HttpClient, private router: Router) {
    this.username = "";
    this.password = "";
    this.repeatedPassword = "";

  }


  onSignIn(): void {
    this.router.navigateByUrl("login");
  }

  onSignUp(): void {
    if (this.password !== this.repeatedPassword) {
      alert("Passwords do not match");
      return;
    }

    const credentials = { username: this.username, password: this.password };
    const url = "http://localhost:8080/registration";
    
    this.http.post<any>(url, credentials)
      .subscribe({
        next: (response) => {
          alert("Registration successful.")
          //store username in session to send it as headers on subsequent requests
          sessionStorage.setItem("currentUser", response.username); 
          this.router.navigateByUrl("dashboard"); // automatically logs in after registration
        },
        error: () => {
          alert("Registration failed.");
        }
      });
  }

}
