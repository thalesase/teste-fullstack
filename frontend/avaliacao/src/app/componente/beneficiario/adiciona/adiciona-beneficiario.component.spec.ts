import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionaPlanoComponent } from './adiciona-plano.component';

describe('PlanoComponent', () => {
  let component: AdicionaPlanoComponent;
  let fixture: ComponentFixture<AdicionaPlanoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdicionaPlanoComponent]
    });
    fixture = TestBed.createComponent(AdicionaPlanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
