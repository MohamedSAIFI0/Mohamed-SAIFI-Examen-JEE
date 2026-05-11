// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ClientListComponent } from './components/clients/client-list/client-list.component';
import { ClientFormComponent } from './components/clients/client-form/client-form.component';
import { ContratListComponent } from './components/contrats/contrat-list/contrat-list.component';
import { ContratFormComponent } from './components/contrats/contrat-form/contrat-form.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/new', component: ClientFormComponent },
  { path: 'clients/edit/:id', component: ClientFormComponent },
  { path: 'contrats', component: ContratListComponent },
  { path: 'contrats/new', component: ContratFormComponent },
  { path: 'contrats/edit/:id', component: ContratFormComponent },
  { path: '**', redirectTo: '' }
];
