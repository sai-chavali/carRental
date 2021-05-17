import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  signinFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.signinFormGroup = this.formBuilder.group({});
   }

  ngOnInit(): void {
    this.signinFormGroup = this.formBuilder.group({
      email: ['',[Validators.required, Validators.email]],
      password: ['',[Validators.required]],
    });
  }

  onSuccess(){
    this.router.navigate(['/home']);
  }

  onError(){
    console.log("Failed");
  }

  signIn() {
    if(this.signinFormGroup.valid){
      this.userService.signInUser(this.signinFormGroup.value).subscribe(res => {
        console.log(res.headers.get('authorization'));
        localStorage.setItem('token', res.headers.get('authorization')||"empty");
        this.onSuccess();
    }, err => {
        console.error('error ', err);
        this.onError();
    });
    }
  }

}
