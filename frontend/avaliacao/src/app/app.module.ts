import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlanoComponent } from './componente/plano/lista/plano.component';
import { InputTextModule } from "primeng/inputtext";
import { TableModule } from 'primeng/table';

import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { SliderModule } from 'primeng/slider';
import {LOCALE_ID} from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PlanoService } from './servico/plano.service';
import { UsuarioService } from './servico/usuario.service';

import { AdicionaPlanoComponent } from './componente/plano/adiciona/adiciona-plano.component';
import { NavComponent } from './componente/nav/nav.component';
import { HttpClientModule } from '@angular/common/http';
import { AdicionaUsuarioComponent } from './componente/usuario/adiciona/adiciona-usuario.component';
import { UsuarioComponent } from './componente/usuario/lista/usuario.component';
import { BeneficiarioService } from './servico/beneficiario.service';
import { BeneficiarioComponent } from './componente/beneficiario/lista/beneficiario.component';
import { AdicionaBeneficiarioComponent } from './componente/beneficiario/adiciona/adiciona-beneficiario.component';
import { LoginComponent } from './componente/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    PlanoComponent,
    AdicionaPlanoComponent,
    NavComponent,
    AdicionaUsuarioComponent,
    UsuarioComponent,
    BeneficiarioComponent,
    AdicionaBeneficiarioComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    InputTextModule,
    TableModule,
    ButtonModule,
    CalendarModule,
    SliderModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [PlanoService,
    UsuarioService,
    BeneficiarioService],
  bootstrap: [AppComponent]
})
export class AppModule { }
