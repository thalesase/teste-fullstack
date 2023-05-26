import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlanoComponent } from './componente/plano/lista/plano.component';
import { UsuarioComponent } from './componente/usuario/lista/usuario.component';
import { AdicionaPlanoComponent } from './componente/plano/adiciona/adiciona-plano.component';
import { AdicionaUsuarioComponent } from './componente/usuario/adiciona/adiciona-usuario.component';
import { BeneficiarioComponent } from './componente/beneficiario/lista/beneficiario.component';
import { AdicionaBeneficiarioComponent } from './componente/beneficiario/adiciona/adiciona-beneficiario.component';
import { LoginComponent } from './componente/login/login.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'plano', component: PlanoComponent },
  { path: 'usuario', component: UsuarioComponent },
  { path: 'beneficiario', component: BeneficiarioComponent },
  { path: 'adicionaPlano', component: AdicionaPlanoComponent },
  { path: 'atualizaPlano/:id', component: AdicionaPlanoComponent },
  { path: 'adicionaUsuario', component: AdicionaUsuarioComponent },
  { path: 'atualizaUsuario/:id', component: AdicionaUsuarioComponent },
  { path: 'adicionaBeneficiario', component: AdicionaBeneficiarioComponent },
  { path: 'atualizaBeneficiario/:id', component: AdicionaBeneficiarioComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
