import { Injectable } from '@angular/core';
import { Beneficiario } from '../modelo/Beneficiario';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class BeneficiarioService {
    readonly apiURL = "http://localhost:8080/beneficiario";
    
    httpOptions = {

    };

    atualizaHeaders() {
        this.httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              'Authorization': 'Basic ' + localStorage.getItem("basic")
            })
        };
    }
    
    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              'Authorization': 'Basic ' + localStorage.getItem("basic")
            })
        };
    }
    
    getBeneficiarios() {
        return this.http.get<Beneficiario>(this.apiURL, this.httpOptions);

    }
    getBeneficiarioPorID(id: number) {
        return this.http.get<Beneficiario>(this.apiURL+"/"+id, this.httpOptions);
    }
    
    addBeneficiario(beneficiario: Beneficiario) {
        return this.http.post<Beneficiario>(this.apiURL, beneficiario, this.httpOptions);
    }
    updateBeneficiario(beneficiario: Beneficiario) {
        return this.http.put<Beneficiario>(this.apiURL+"/"+beneficiario.id, beneficiario, this.httpOptions);
    }
    removeBeneficiario(id: number) {
        return this.http.delete(this.apiURL+"/"+id, this.httpOptions);
    }
}