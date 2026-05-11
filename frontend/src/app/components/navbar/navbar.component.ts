import { Component } from '@angular/core';
import {RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  imports: [
    RouterLinkActive
  ],
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {}
