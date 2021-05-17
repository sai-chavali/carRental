import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
  token: String|null = "";
  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler) {
    this.token = localStorage.getItem('token');
    request = request.clone({
      headers: request.headers.set('Authorization', `${this.token}`)
    });
    return next.handle(request);
  }
}
