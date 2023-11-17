import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { first } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly  API = '../../../assets/users.json';// 'https://api-bootcamp-santander.onrender.com/users';
  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<User[]>(this.API)
    .pipe(first());
  }
}
