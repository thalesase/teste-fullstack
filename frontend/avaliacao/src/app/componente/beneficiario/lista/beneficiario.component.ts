import { Component, OnInit } from '@angular/core';
import { Beneficiario } from 'src/app/modelo/Beneficiario';
import { Router } from '@angular/router';
import { BeneficiarioService } from 'src/app/servico/beneficiario.service';
import { UsuarioService } from 'src/app/servico/usuario.service';


@Component({
  selector: 'app-beneficiario',
  templateUrl: './beneficiario.component.html',
  styleUrls: ['./beneficiario.component.css']
})
export class BeneficiarioComponent implements OnInit{
    beneficiarioLista: Beneficiario[] = [];
    first = 0;
    rows = 10;
    constructor(private beneficiarioService: BeneficiarioService, private router : Router, private usuarioService : UsuarioService) {}
    ngOnInit(): void {
        this.usuarioService.verificaUsuario().subscribe((response) => {
        },
        (error) => {             
          if(error.status == 401) this.router.navigate(['/login']);
    
        });
        this.beneficiarioService.getBeneficiarios().subscribe((data: any) => {
            this.beneficiarioLista = data;
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
        return this.beneficiarioLista ? this.first === (this.beneficiarioLista.length - this.rows) : true;
    }
    isFirstPage(): boolean {
        return this.beneficiarioLista ? this.first === 0 : true;
    }

    remove(id: number) {
        this.beneficiarioService.removeBeneficiario(id).subscribe();
        
        this.router.navigate([this.router.url])
    }
}
