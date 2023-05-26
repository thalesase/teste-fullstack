import { Injectable } from '@angular/core';
import { Plano } from '../modelo/Plano';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PlanoService {
    readonly apiURL = "http://localhost:8080/plano";
    


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
    getPlanos() {
        return this.http.get<Plano>(this.apiURL, this.httpOptions);

    }
    getPlanoPorID(id: number) {
        return this.http.get<Plano>(this.apiURL+"/"+id, this.httpOptions);
    }
    
    addPlano(plano: Plano) {
        return this.http.post<Plano>(this.apiURL, plano, this.httpOptions);
    }
    updatePlano(plano: Plano) {
        return this.http.put<Plano>(this.apiURL+"/"+plano.id, plano, this.httpOptions);
    }
    removePlano(id: number) {
        return this.http.delete(this.apiURL+"/"+id, this.httpOptions);
    }
}