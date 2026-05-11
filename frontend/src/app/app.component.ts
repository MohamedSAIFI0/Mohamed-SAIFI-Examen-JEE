import { Component } from '@angular/core';
import {NavbarComponent} from './components/navbar/navbar.component';
import {RouterOutlet} from '@angular/router';


@Component({
  selector: 'app-root',
  template: `
    <app-navbar></app-navbar>
    <main class="main-content">
      <router-outlet></router-outlet>
    </main>
  `,
  imports: [
    NavbarComponent,
    RouterOutlet
  ],
  styles: [`
    .main-content {
      min-height: calc(100vh - 70px);
      background: #f8f9fa;
    }
  `]
})
export class AppComponent {
  title = 'frontend';
}


