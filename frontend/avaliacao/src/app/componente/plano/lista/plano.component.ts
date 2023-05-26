import { Component, OnInit } from '@angular/core';
import { Plano } from '../../../modelo/Plano';
import { PlanoService } from '../../../servico/plano.service';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/servico/usuario.service';


@Component({
  selector: 'app-plano',
  templateUrl: './plano.component.html',
  styleUrls: ['./plano.component.css']
})
export class PlanoComponent implements OnInit{
  planosLista: Plano[] = [];
    first = 0;
    rows = 10;
    constructor(private planoService: PlanoService, private router : Router, private usuarioService:UsuarioService) {}
    ngOnInit(): void {
        this.usuarioService.verificaUsuario().subscribe((response) => {
        },
        (error) => {             
          if(error.status == 401) this.router.navigate(['/login']);
    
        });
        this.planoService.getPlanos().subscribe((data: any) => {
            this.planosLista = data;
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
        return this.planosLista ? this.first === (this.planosLista.length - this.rows) : true;
    }
    isFirstPage(): boolean {
        return this.planosLista ? this.first === 0 : true;
    }

    remove(id: number) {
        this.planoService.removePlano(id).subscribe();
        
        this.router.navigate([this.router.url])
    }
}
