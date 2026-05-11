import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import { ClientService } from '../../../services/client.service';
import {CommonModule, NgClass} from '@angular/common';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass,
    CommonModule, RouterModule, FormsModule
  ],
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent implements OnInit {

  form!: FormGroup;
  isEdit = false;
  clientId?: number;

  submitted = false;
  saving = false;

  message = '';
  messageType: 'success' | 'error' = 'success';

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {

    this.form = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      adresse: [''],
      dateNaissance: ['']
    });

    this.clientId = Number(this.route.snapshot.params['id']);

    if (this.clientId) {
      this.isEdit = true;

      this.clientService.getById(this.clientId)
        .subscribe(client => this.form.patchValue(client));
    }
  }

  get f() {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) return;

    this.saving = true;

    const request = this.isEdit
      ? this.clientService.update(this.clientId!, this.form.value)
      : this.clientService.create(this.form.value);

    request.subscribe({
      next: () => this.router.navigate(['/clients']),
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
