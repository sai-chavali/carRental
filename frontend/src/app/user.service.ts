import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {concatMap, finalize, map} from 'rxjs/operators';
import { User, UserBookingsResponse } from './models';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  res: Observable<any>;
  user: Observable<User|null>;

  constructor(private httpClient: HttpClient) { }

  registerUser(user:any){
    return this.httpClient.post<any>('/api/users/add', user).pipe(
      finalize(() => {
        console.log("Call completed");
      })
    )
  }

  signInUser(user:any){
    return this.httpClient.post<User>('/api/users/login', user,{observe: 'response'}).pipe(
      finalize(() => {
        this.getUser();
      }));
  }

  getUser(){
    this.user = this.httpClient.get<User>('/api/users/me').pipe(map(user => user));
  }

  logOut(){
    this.user = new Observable<null>();
    return this.httpClient.post<any>('/api/users/logOut',{});
  }

  getMyBookings(){
    return this.httpClient.get<UserBookingsResponse>('/api/users/mybookings')
  }
}
