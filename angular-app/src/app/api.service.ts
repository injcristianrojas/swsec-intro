import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  login(username, password) {
    return this.http.post(
      'http://127.0.0.1:8080/api/auth/login',
      {'username': username, 'password': password},
      { observe: 'response' }
    )
  }
  
  getPosts() {
    return this.http.get('http://127.0.0.1:8080/api/posts/get');
  }

  insertPost(message: string) {
    return this.http.post(
      'http://127.0.0.1:8080/api/posts/add',
      {'message': message}
    )
  }
}
