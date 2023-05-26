import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Plano } from 'src/app/modelo/Plano';
import { BeneficiarioService } from 'src/app/servico/beneficiario.service';
import { PlanoService } from 'src/app/servico/plano.service';
import { UsuarioService } from 'src/app/servico/usuario.service';

@Component({
  selector: 'app-adiciona-beneficiario',
  templateUrl: './adiciona-beneficiario.component.html',
  styleUrls: ['./adiciona-beneficiario.component.css']
})
export class AdicionaBeneficiarioComponent implements OnInit{
    id: number = 0;
    beneficiarioForm: FormGroup;
    planosLista: Plano[] = [];
    plano: Plano | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private beneficiarioService: BeneficiarioService,
    private planoService: PlanoService,
    private usuarioService: UsuarioService

  ) {
    this.beneficiarioForm = this.fb.group({
      id: [0, [Validators.required]],
      nome: ['', [Validators.required]],
      idade: ['', [Validators.required, Validators.min(0)]],
      email: ['', [Validators.required]],
      cpf: ['', [Validators.required]],
      plano: [''],
    });

  }

  ngOnInit(): void {
    this.usuarioService.verificaUsuario().subscribe((response) => {
    },
    (error) => {             
      if(error.status == 401) this.router.navigate(['/login']);

    });
    this.planoService.getPlanos().subscribe((data: any) => {
      this.planosLista = data;
    });

    this.route.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      if (params['id'] != null) {
        this.beneficiarioForm.get('Id')?.setValue(params['id']);
        this.beneficiarioService.getBeneficiarioPorID(this.id).subscribe((data: any) => {
          if (data) {
            this.beneficiarioForm.setValue(data);
            this.plano = data.plano;
          }
        }); 
      }
    });

  }

  save() {
    if (this.beneficiarioForm.invalid)
      return

      console.log(this.plano);
    if(this.plano != undefined && this.plano != null) 
      this.beneficiarioForm.value["plano"] = this.plano;
    
    if (this.beneficiarioForm.get('id')?.value === 0) {
      this.beneficiarioService.addBeneficiario(this.beneficiarioForm.value).subscribe();
    } else {
      this.beneficiarioService.updateBeneficiario(this.beneficiarioForm.value).subscribe();
    }

    this.router.navigate(['/beneficiario']);
  }
}
