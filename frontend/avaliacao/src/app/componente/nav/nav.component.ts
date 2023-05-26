import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BeneficiarioService } from 'src/app/servico/beneficiario.service';
import { PlanoService } from 'src/app/servico/plano.service';
import { UsuarioService } from 'src/app/servico/usuario.service';



@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  
  constructor(private usuarioServico:UsuarioService, private planoServico:PlanoService, private beneficiarioServico:BeneficiarioService, private router : Router) {}

  logout() {
    localStorage.removeItem("basic");
    this.usuarioServico.atualizaHeaders();
    this.planoServico.atualizaHeaders();
    this.beneficiarioServico.atualizaHeaders();

    this.router.navigate(['/login'])

  }
}
