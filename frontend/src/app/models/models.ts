export interface Client {
  id?: number;
  nom: string;
  prenom: string;
  email: string;
  telephone: string;
  adresse: string;
  dateNaissance?: string;
}

export interface Contrat {
  id?: number;
  reference: string;
  typeAssurance: string;
  dateDebut: string;
  dateFin: string;
  montantPrime: number;
  statut: 'ACTIF' | 'EXPIRE' | 'RESILIE' | 'EN_ATTENTE';
  client?: Client;
  clientId?: number;
  description?: string;
}

export type TypeAssurance = 'AUTO' | 'HABITATION' | 'VIE' | 'SANTE' | 'VOYAGE';
export type StatutContrat = 'ACTIF' | 'EXPIRE' | 'RESILIE' | 'EN_ATTENTE';
