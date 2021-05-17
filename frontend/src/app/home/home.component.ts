import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CarService } from '../car.service';
import { CustomSnackbarService } from '../custom-snackbar.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { Car } from '../models';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  dataSource = new MatTableDataSource<Car>();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  displayedColumns =
      ['name', 'model', 'made', 'color', 'price', 'status','action'];

  constructor(private carService: CarService, public dialog: MatDialog,  public cs:CustomSnackbarService) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.carService.getCars().subscribe(cars=> {
      this.dataSource.data = cars;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  openDialog(action: any,obj: any) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '50%',
      data:obj
    });

    dialogRef.afterClosed().subscribe(result => {
       if(result.event == 'Update'){
        this.updateRowData(result.data);
      }else if(result.event == 'Delete'){
        this.deleteRowData(result.data);
      }
    });
  }

  onSuccess(str:string){
    this.cs.open(str);
  }

  updateRowData(row_obj:any){
   console.log(`row_obj`, row_obj);

   this.carService.updateCar(row_obj).subscribe((res) => {
       this.onSuccess(res);
     },
     (err) => {
       console.error('error ', err);
    });
    
    this.dataSource.data = this.dataSource.data.filter((value,key)=>{
      if(value.id == row_obj.id){
        value.name = row_obj.name;
        value.model = row_obj.model;
        value.made = row_obj.made;
        value.price = row_obj.price;
        value.color = row_obj.color;
      }
      return true;
    });
  }


  deleteRowData(row_obj:any ){
    this.carService.deleteCar(row_obj.id).subscribe((res) => {
      this.onSuccess(res);
    },
    (err) => {
      console.error('error ', err);
   });
    this.dataSource.data = this.dataSource.data.filter((value,key)=>{
      return value.id != row_obj.id;
    });
  }
}
