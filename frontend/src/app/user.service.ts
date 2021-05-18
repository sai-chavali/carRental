import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import {concatMap, finalize, map} from 'rxjs/operators';
import { User, UserBookingsResponse } from './models';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loggedIn =  new BehaviorSubject<Boolean>(true);
  isloggedIn$ = this.loggedIn.asObservable().pipe(
    map(user => user === false)
  )


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
        this.loggedIn.next(false);
      }));
  }

  getUser(){
    this.httpClient.get<User>('/api/users/me').subscribe(res => {
      this.loggedIn.next(false);
    }, err => {
      console.error('error ', err);
      this.loggedIn.next(true);
    })
  }

  logOut(){
    return this.httpClient.post<any>('/api/users/logOut',{}).pipe(finalize(() => {
      this.loggedIn.next(true);
    }));
  }

  getMyBookings(){
    return this.httpClient.get<UserBookingsResponse>('/api/users/mybookings')
  }
}
