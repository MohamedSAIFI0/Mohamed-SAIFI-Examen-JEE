import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ContratService } from '../../services/contrat.service';
import { Contrat } from '../../models/models';
import {CommonModule, DecimalPipe, NgClass} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,

  imports: [
    NgClass,
    DecimalPipe,
    CommonModule, RouterModule, FormsModule
  ],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  totalClients = 0;
  totalContrats = 0;
  contratsActifs = 0;
  primesTotales = 0;
  recentContrats: Contrat[] = [];
  loading = true;

  constructor(
    private clientService: ClientService,
    private contratService: ContratService
  ) {}

  ngOnInit() {
    this.clientService.getAll().subscribe(clients => {
      this.totalClients = clients.length;
    });

    this.contratService.getAll().subscribe({
      next: (contrats) => {
        this.loading = false;

        this.totalContrats = contrats.length;
        this.contratsActifs = contrats.filter(c => c.statut === 'ACTIF').length;
        this.primesTotales = contrats.reduce((sum, c) => sum + c.montantPrime, 0);
        this.recentContrats = contrats.slice(0, 5);
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  typeBadge(type: string): string {
    const map: any = {
      AUTO: 'bg-info text-dark',
      HABITATION: 'bg-warning text-dark',
      VIE: 'bg-primary',
      SANTE: 'bg-success',
      VOYAGE: 'bg-secondary'
    };
    return map[type] || 'bg-secondary';
  }

  statutBadge(statut: string): string {
    const map: any = {
      ACTIF: 'bg-success',
      EXPIRE: 'bg-secondary',
      RESILIE: 'bg-danger',
      EN_ATTENTE: 'bg-warning text-dark'
    };
    return map[statut] || 'bg-secondary';
  }
}
