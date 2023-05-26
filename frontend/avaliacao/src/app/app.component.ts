import { Component } from '@angular/core';
import {Router, NavigationEnd,ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'avaliacao';
  mySubscription;

  constructor(private router: Router, private activatedRoute: ActivatedRoute){
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
         this.router.navigated = false;
      }
    }); 
 }

 ngOnDestroy(){
  if (this.mySubscription) {
   this.mySubscription.unsubscribe();
  }
}
}
