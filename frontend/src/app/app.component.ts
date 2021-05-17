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
  user$: Observable<User|null>;


  constructor(private userService: UserService,  private router: Router) {}

  ngOnInit() {
    this.userService.getUser();
    this.user$ = this.userService.user.pipe(map(res => res))
    console.log(`user$`, this.user$);
  }

  singOut() {
    this.userService.logOut().subscribe(res => {
      console.log(res);
      this.router.navigate(['/','signin'])
    });
    localStorage.removeItem('token');
  }

}
