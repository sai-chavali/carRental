import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CarService } from '../car.service';
import { CustomSnackbarService } from '../custom-snackbar.service';

@Component({
  selector: 'app-create-car',
  templateUrl: './create-car.component.html',
  styleUrls: ['./create-car.component.scss'],
})
export class CreateCarComponent implements OnInit {
  carFormGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private carService: CarService,
    private router: Router,
    private customSnackbarService: CustomSnackbarService
  ) {
    this.carFormGroup = formBuilder.group({});
  }

  ngOnInit(): void {
    this.carFormGroup = this.formBuilder.group({
      name: ['', [Validators.required]],
      model: ['', [Validators.required]],
      made: ['', [Validators.required]],
      color: ['', [Validators.required]],
      price: ['', [Validators.required]],
    });
  }
  onSuccess(str:string){
    this.customSnackbarService.open(str);
  }
  
  get model() {return this.carFormGroup.get('model'); }
  get name() {return this.carFormGroup.get('name'); }
  get made() {return this.carFormGroup.get('made'); }
  get color() {return this.carFormGroup.get('color'); }
  get price() {return this.carFormGroup.get('price'); }

  onError(){
    console.log("Failed");
    this.customSnackbarService.open("Something went wrong", 'error');
  }

  addCar() {
    if (this.carFormGroup.valid) {
      this.carService.addCar(this.carFormGroup.value).subscribe(
        (res) => {
          this.onSuccess(res);
        },
        (err) => {
          console.error('error ', err);
          this.onError();
        }
      );
    }
  }
}
