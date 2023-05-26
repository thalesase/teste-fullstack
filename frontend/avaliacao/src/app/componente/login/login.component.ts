import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BeneficiarioService } from 'src/app/servico/beneficiario.service';
import { PlanoService } from 'src/app/servico/plano.service';
import { UsuarioService } from 'src/app/servico/usuario.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
    loginForm: FormGroup;
    error: String = "";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private planoService : PlanoService,
    private beneficiarioService : BeneficiarioService
  ) {
    this.loginForm = this.fb.group({
      login: ['', [Validators.required]],
      senha: ['', [Validators.required]],
    });

  }

  ngOnInit(): void {
  }

  login() {
    if (this.loginForm.invalid)
      return

    localStorage.setItem("basic", btoa(this.loginForm.value.login+":"+this.loginForm.value.senha));             

    this.usuarioService.atualizaHeaders();
    this.planoService.atualizaHeaders();
    this.beneficiarioService.atualizaHeaders();
    this.usuarioService.verificaUsuario().subscribe((response) => {
      this.router.navigate(['/plano']);
    },
    (error) => {             
      if(error.status == 401) this.error = "Usuário ou senha incorretos"
      else this.error = "Estamos com uma instabilidade, tente novamente mais tarde"
    });
   

  }
}
