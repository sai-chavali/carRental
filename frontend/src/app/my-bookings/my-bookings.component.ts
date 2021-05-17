import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CarService } from '../car.service';
import { CustomSnackbarService } from '../custom-snackbar.service';
import { UserBookings, UserBookingsResponse } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss']
})
export class MyBookingsComponent implements OnInit {

  dataSource = new MatTableDataSource<UserBookings>();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  displayedColumns =
      ['name', 'model', 'start_time', 'end_time', 'booked_at','price'];

  constructor(private userService: UserService, public dialog: MatDialog,  public cs:CustomSnackbarService) {
  }

  ngOnInit(): void {
    this.userService.getMyBookings().subscribe(res => {
      this.dataSource.data = res.userBookings;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

}
