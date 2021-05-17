import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { finalize } from 'rxjs/operators';
import { Car, CarBookingsResponse } from './models';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private httpClient: HttpClient) { }

  getCars() {
    return this.httpClient.get<Car[]>("/api/catalog").pipe(
      finalize(() => {
          console.log("Call ended");
      })
    );
  }

  addCar(car: any){
    console.log(`car`, car);
    return this.httpClient.post<any>('/api/catalog/addCar',car).pipe(
      finalize(() => {
        console.log("Call ended");
      })
    );
  }

  updateCar(car:Car){
    return this.httpClient.put<any>(`/api/catalog/${car.id}`, car).pipe(
      finalize(() => {
        console.log("Call ended");
      })
    );
  }

  deleteCar(id: number){
    return this.httpClient.delete<any>(`/api/catalog/${id}`).pipe(
      finalize(() => {
        console.log("Call ended");
      })
    );
  }

  getCar(id: string|null){
    return this.httpClient.get<CarBookingsResponse>(`api/catalog/${id}`).pipe(
      finalize(() => {
        console.log(`Call ened`);
      })
    )
  }

  addBooking(id:string|null, start_time: Date,end_time:Date){
    console.log(id, start_time, end_time);
    return this.httpClient.post<any>(`/api/users/book/${id}`,{start_time,end_time});
  }

}
