import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {ActivatedRoute, Router, RouterLink, RouterModule} from '@angular/router';
import { ContratService } from '../../../services/contrat.service';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/models';
import {CommonModule, NgClass} from '@angular/common';

@Component({
  selector: 'app-contrat-form',
  templateUrl: './contrat-form.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass,
    CommonModule, RouterModule, FormsModule
  ],
  styleUrls: ['./contrat-form.component.css']
})
export class ContratFormComponent implements OnInit {

  form!: FormGroup;
  clients: Client[] = [];

  isEdit = false;
  contratId?: number;

  submitted = false;
  saving = false;

  message = '';
  messageType: 'success' | 'error' = 'success';

  constructor(
    private fb: FormBuilder,
    private contratService: ContratService,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.form = this.fb.group({
      reference: ['', Validators.required],
      typeAssurance: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      montantPrime: [null, [Validators.required, Validators.min(0)]],
      statut: ['EN_ATTENTE'],
      clientId: [null, Validators.required],
      description: ['']
    });

    this.loadClients();

    this.contratId = Number(this.route.snapshot.params['id']);

    if (this.contratId) {
      this.isEdit = true;

      this.contratService.getById(this.contratId)
        .subscribe(c => {
          this.form.patchValue({
            ...c,
            clientId: c.client?.id || c.clientId
          });
        });
    }
  }

  loadClients() {
    this.clientService.getAll()
      .subscribe(data => this.clients = data);
  }

  get f() {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) return;

    this.saving = true;

    const request = this.isEdit
      ? this.contratService.update(this.contratId!, this.form.value)
      : this.contratService.create(this.form.value);

    request.subscribe({
      next: () => this.router.navigate(['/contrats']),
      error: () => {
        this.showMessage("Erreur lors de l'enregistrement", 'error');
        this.saving = false;
      }
    });
  }

  showMessage(msg: string, type: 'success' | 'error') {
    this.message = msg;
    this.messageType = type;

    setTimeout(() => this.message = '', 3000);
  }
}
