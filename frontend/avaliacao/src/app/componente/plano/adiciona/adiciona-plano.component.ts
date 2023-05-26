import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanoService } from '../../../servico/plano.service';
import { UsuarioService } from 'src/app/servico/usuario.service';

@Component({
  selector: 'app-adiciona-plano',
  templateUrl: './adiciona-plano.component.html',
  styleUrls: ['./adiciona-plano.component.css']
})
export class AdicionaPlanoComponent implements OnInit{
    id: number = 0;
    planoform: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private planoService: PlanoService,
    private usuarioService: UsuarioService
  ) {
    this.planoform = this.fb.group({
      id: [0, [Validators.required]],
      nome: ['', [Validators.required]],
      valor: ['', [Validators.required, Validators.min(0)]],
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
        this.planoform.get('Id')?.setValue(params['id']);
        this.planoService.getPlanoPorID(this.id).subscribe((data: any) => {
          if (data) {
            this.planoform.setValue(data);
          }
        });
        
        
      }
    });
  }

  save() {
    if (this.planoform.invalid)
      return

    if (this.planoform.get('id')?.value === 0) {
      this.planoService.addPlano(this.planoform.value).subscribe();
    } else {
      this.planoService.updatePlano(this.planoform.value).subscribe();
    }

    this.router.navigate(['/plano']);
  }
}
