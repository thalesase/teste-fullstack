import { Injectable } from '@angular/core';
import { Usuario } from '../modelo/Usuario';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
    readonly apiURL = "http://localhost:8080/usuario";
    
    httpOptions = {

    };

    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              'Authorization': 'Basic ' + localStorage.getItem("basic")
            })
        };
    }

    atualizaHeaders() {
        this.httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json',
              'Authorization': 'Basic ' + localStorage.getItem("basic")
            })
        };
    }

    getUsuarios() {
        return this.http.get<Usuario>(this.apiURL, this.httpOptions);

    }
    getUsuarioPorLogin(id: string) {
        return this.http.get<Usuario>(this.apiURL+"/"+id, this.httpOptions);
    }
    
    addUsuario(usuario: Usuario) {
        return this.http.post<Usuario>(this.apiURL, usuario, this.httpOptions);
    }
    updateUsuario(usuario: Usuario) {
        return this.http.put<Usuario>(this.apiURL+"/"+usuario.login, usuario, this.httpOptions);
    }
    removeUsuario(id: string) {
        return this.http.delete(this.apiURL+"/"+id, this.httpOptions);
    }

    verificaUsuario() {

        return this.http.get<Usuario>(this.apiURL, this.httpOptions);

    }
}