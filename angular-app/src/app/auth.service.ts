import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  expiration_time: number;

  constructor() { }

  setToken(token: string) {
    const tokenpayload = jwt_decode(token);
    this.expiration_time = tokenpayload.exp;
    localStorage.setItem('jwtToken', token);
  }

  getToken() {
    return localStorage.getItem('jwtToken');
  }

  removeToken() {
    localStorage.removeItem('jwtToken');
  }

  isSessionOpen(): boolean {
    const current_time = Date.now() / 1000
    if (this.expiration_time < current_time)
      this.removeToken();
    return this.getToken() !== null;
  }

}
