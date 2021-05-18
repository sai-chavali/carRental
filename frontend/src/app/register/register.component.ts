import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomSnackbarService } from '../custom-snackbar.service';
import { UserService } from '../user.service';
import { passwordMatchValidator } from './passwordMatchValidator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  minPw = 8;
  registerFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router, public cs:CustomSnackbarService) {
    this.registerFormGroup = this.formBuilder.group({});
   }

  ngOnInit(): void {
    this.registerFormGroup = this.formBuilder.group({
      username: ['',[Validators.required]],
      email: ['',[Validators.required, Validators.email]],
      password: ['',[Validators.required, Validators.minLength(this.minPw)]],
      password2: ['',[Validators.required]]
    }, { validator: passwordMatchValidator });
  }

  get password() { return this.registerFormGroup.get('password'); }
  get password2() { return this.registerFormGroup.get('password2'); }
  get email() {return this.registerFormGroup.get('email'); }
  get username() {return this.registerFormGroup.get('username'); }

  onPasswordInput() {
    if (this.registerFormGroup.hasError('passwordMismatch'))
      this.password2?.setErrors([{'passwordMismatch': true}]);
    else
      this.password2?.setErrors(null);
  }

  onSuccess(){
    this.router.navigate(['/','signin']);
    this.cs.open("User Registered Successfully");
  }

  onError(){
    console.log("Failed");
  }

  addUser(){
    if(this.registerFormGroup.valid){
      console.log(`save user Details`);
      this.registerFormGroup.removeControl("password2");
      console.log(`this.registerFormGroup.value`, this.registerFormGroup.value);
      this.userService.registerUser(this.registerFormGroup.value).subscribe(res => {
        this.onSuccess();
    }, err => {
        console.error('error ', err);
        this.onError();
    });
    }
  }

}
