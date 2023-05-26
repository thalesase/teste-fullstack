import { Plano } from "./Plano";

export interface Beneficiario {
    id: number;
    nome: string,
    email: string,
    cpf: string
    idade: number,
    plano: Plano;
}