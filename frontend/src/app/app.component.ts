import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from './models';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  isloggedIn$: Observable<boolean>;


  constructor(private userService: UserService,  private router: Router) {
    this.isloggedIn$ = this.userService.isloggedIn$
  }

  ngOnInit() {
    this.userService.getUser();
  }

  singOut() {
    this.userService.logOut().subscribe(res => {
      console.log(res);
      this.router.navigate(['/','signin'])
    });
    localStorage.removeItem('token');
  }

}
