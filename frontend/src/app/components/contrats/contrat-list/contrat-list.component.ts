import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, RouterLink, RouterModule} from '@angular/router';
import { ContratService } from '../../../services/contrat.service';
import { Contrat } from '../../../models/models';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, DatePipe, DecimalPipe, NgClass} from '@angular/common';

@Component({
  selector: 'app-contrat-list',
  templateUrl: './contrat-list.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgClass,
    RouterLink,
    DatePipe,
    DecimalPipe,
    CommonModule, RouterModule, FormsModule
  ],
  styleUrls: ['./contrat-list.component.css']
})
export class ContratListComponent implements OnInit {

  contrats: Contrat[] = [];
  filteredContrats: Contrat[] = [];

  searchTerm = '';
  filterStatut = '';
  filterType = '';

  loading = true;

  message = '';
  messageType: 'success' | 'error' = 'success';

  constructor(
    private contratService: ContratService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadContrats();
  }

  loadContrats() {
    this.loading = true;

    this.contratService.getAll().subscribe({
      next: (data) => {
        this.contrats = data;
        this.applyFilters();
        this.loading = false;
      },
      error: () => {
        this.showMessage('Erreur de chargement', 'error');
        this.loading = false;
      }
    });
  }

  applyFilters() {
    this.filteredContrats = this.contrats.filter(c => {

      const matchSearch =
        !this.searchTerm ||
        c.reference.toLowerCase().includes(this.searchTerm.toLowerCase());

      const matchStatut =
        !this.filterStatut || c.statut === this.filterStatut;

      const matchType =
        !this.filterType || c.typeAssurance === this.filterType;

      return matchSearch && matchStatut && matchType;
    });
  }

  clearFilters() {
    this.searchTerm = '';
    this.filterStatut = '';
    this.filterType = '';
    this.applyFilters();
  }

  deleteContrat(c: Contrat) {
    if (!confirm(`Supprimer le contrat ${c.reference} ?`)) return;

    this.contratService.delete(c.id!).subscribe({
      next: () => {
        this.showMessage('Contrat supprimé', 'success');
        this.loadContrats();
      },
      error: () => {
        this.showMessage('Erreur lors de la suppression', 'error');
      }
    });
  }

  typeBadge(type: string): string {
    const map: any = {
      AUTO: 'bg-info text-dark',
      HABITATION: 'bg-warning text-dark',
      VIE: 'bg-primary',
      SANTE: 'bg-success',
      VOYAGE: 'bg-purple text-white'
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

  showMessage(msg: string, type: 'success' | 'error') {
    this.message = msg;
    this.messageType = type;

    setTimeout(() => this.message = '', 3000);
  }
}
