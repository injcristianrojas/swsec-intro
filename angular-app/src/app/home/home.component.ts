import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ApiService } from '../api.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private api: ApiService, private auth: AuthService) { }

  ngOnInit(): void {
  }

  login(username, password): void {
    this.api.login(username, password).subscribe(
      data => {
        this.auth.setToken(data.headers.get('Authorization'));
        this.loginForm.reset();
      },
      error => {
        console.log(error);
      }
    );
  }

  isSessionOpen(): boolean {
    return this.auth.isSessionOpen();
  }

  onSubmit(): void {
    this.login(
      this.loginForm.value.username,
      this.loginForm.value.password
    )
  }

}
