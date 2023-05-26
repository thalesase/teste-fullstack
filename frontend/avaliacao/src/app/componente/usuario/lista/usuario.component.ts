import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Usuario } from 'src/app/modelo/Usuario';
import { UsuarioService } from 'src/app/servico/usuario.service';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit{
  usuarioLista: Usuario[] = [];
    first = 0;
    rows = 10;
    constructor(private usuarioService: UsuarioService, private router : Router) {}
    ngOnInit(): void {
        this.usuarioService.verificaUsuario().subscribe((response) => {
        },
        (error) => {             
          if(error.status == 401) this.router.navigate(['/login']);
    
        });
        this.usuarioService.getUsuarios().subscribe((data: any) => {
            this.usuarioLista = data;
        });
    }

    next() {
        this.first = this.first + this.rows;
    }
    prev() {
        this.first = this.first - this.rows;
    }
    reset() {
        this.first = 0;
    }
    isLastPage(): boolean {
        return this.usuarioLista ? this.first === (this.usuarioLista.length - this.rows) : true;
    }
    isFirstPage(): boolean {
        return this.usuarioLista ? this.first === 0 : true;
    }

    remove(id: string) {
        this.usuarioService.removeUsuario(id).subscribe();
        
        this.router.navigate([this.router.url])
    }
}
