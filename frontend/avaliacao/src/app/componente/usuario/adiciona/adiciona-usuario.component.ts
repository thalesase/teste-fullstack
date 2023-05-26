import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from 'src/app/servico/usuario.service';

@Component({
  selector: 'app-adiciona-usuario',
  templateUrl: './adiciona-usuario.component.html',
  styleUrls: ['./adiciona-usuario.component.css']
})
export class AdicionaUsuarioComponent implements OnInit{
    id: string = '';
    usuarioForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private usuarioService: UsuarioService
  ) {
    this.usuarioForm = this.fb.group({
      login: ['', [Validators.required]],
      nome: ['', [Validators.required]],
      senha: ['', [Validators.required]],
    });

  }

  ngOnInit(): void {
    this.usuarioService.verificaUsuario().subscribe((response) => {
    },
    (error) => {             
      if(error.status == 401) this.router.navigate(['/login']);

    });

    this.route.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      if (params['id'] != null) {
        this.usuarioForm.get('login')?.setValue(params['id']);
        this.usuarioService.getUsuarioPorLogin(this.id).subscribe((data: any) => {
          if (data) {
            this.usuarioForm.setValue(data);
          }
        });
        
        
      }
    });
  }

  save() {
    if (this.usuarioForm.invalid)
      return

      console.log()
    if (this.id === '' || this.id === undefined) {
      this.usuarioService.addUsuario(this.usuarioForm.value).subscribe();
    } else {
      this.usuarioService.updateUsuario(this.usuarioForm.value).subscribe();
    }

    this.router.navigate(['/usuario']);
  }
}
