import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/models';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, NgClass} from '@angular/common';
import {RouterLink, RouterModule} from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgClass,
    RouterLink,
    CommonModule, RouterModule, FormsModule
  ],
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Client[] = [];
  filteredClients: Client[] = [];

  searchTerm = '';
  loading = true;

  message = '';
  messageType: 'success' | 'error' = 'success';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients() {
    this.loading = true;

    this.clientService.getAll().subscribe({
      next: (data) => {
        this.clients = data;
        this.filteredClients = data;
        this.loading = false;
      },
      error: () => {
        this.showMessage('Erreur de chargement', 'error');
        this.loading = false;
      }
    });
  }

  onSearch() {
    const term = this.searchTerm.toLowerCase();

    this.filteredClients = this.clients.filter(c =>
      c.nom.toLowerCase().includes(term) ||
      c.prenom.toLowerCase().includes(term) ||
      c.email.toLowerCase().includes(term) ||
      c.telephone.includes(term)
    );
  }

  clearSearch() {
    this.searchTerm = '';
    this.filteredClients = this.clients;
  }

  deleteClient(client: Client) {
    if (!confirm(`Supprimer ${client.nom} ${client.prenom} ?`)) return;

    this.clientService.delete(client.id!).subscribe({
      next: () => {
        this.showMessage('Client supprimé avec succès', 'success');
        this.loadClients();
      },
      error: () => {
        this.showMessage('Erreur lors de la suppression', 'error');
      }
    });
  }

  showMessage(msg: string, type: 'success' | 'error') {
    this.message = msg;
    this.messageType = type;

    setTimeout(() => this.message = '', 3000);
  }
}
