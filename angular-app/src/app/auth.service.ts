import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  setToken(token: string) {
    localStorage.setItem('jwtToken', token);
  }

  getToken() {
    return localStorage.getItem('jwtToken');
  }

  removeToken() {
    localStorage.removeItem('jwtToken');
  }

  isSessionOpen(): boolean {
    return this.getToken() !== null;
  }

}
