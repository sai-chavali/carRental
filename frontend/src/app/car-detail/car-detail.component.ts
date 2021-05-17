import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { CarService } from '../car.service';
import { Car, CarBookings, CarBookingsResponse } from '../models';

@Component({
  selector: 'app-car-detail',
  templateUrl: './car-detail.component.html',
  styleUrls: ['./car-detail.component.scss']
})
export class CarDetailComponent implements OnInit {
  id: string|null;
  car: Car;
  dataSource = new MatTableDataSource<CarBookings>();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  displayedColumns = ['username', 'email', 'start_time', 'end_time']

  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  constructor(private carService:CarService, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.router.snapshot.paramMap.get("id");
    this.carService.getCar(this.id).subscribe(res => {
      this.dataSource.data = res.carBookings;
      this.car = res.car;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  book(){
    this.carService.addBooking(this.id, this.range.value.start, this.range.value.end).subscribe(res => {
      console.log(res);
    });
  }

}
