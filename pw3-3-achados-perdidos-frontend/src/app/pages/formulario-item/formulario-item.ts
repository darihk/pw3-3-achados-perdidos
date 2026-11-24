
import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ItemPerdidoService } from '../../services/item-perdido-service';
import { ItemPerdido, NovoItemPerdido } from '../../models/item-perdido';

@Component({
  selector: 'app-formulario-item',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './formulario-item.html',
  styleUrl: './formulario-item.css',
})
export class FormularioItem implements OnInit {

  formulario: FormGroup;
  idItem: number | null = null;

  mensagemSucesso = signal('');
  mensagemErro = signal('');

  carregandoItem = signal(false);
  enviandoFormulario = signal(false);
  mostrarAviso = signal(false);

  constructor(
    private fb: FormBuilder,
    private itemService: ItemPerdidoService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.formulario = this.fb.group({
      nomeItem: ['', [Validators.required, Validators.minLength(2)]],
      localEncontrado: ['', Validators.required],
      dataAchado: ['', Validators.required],
      descricaoDevolucao: ['']
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.idItem = Number(id);
      this.carregarItem(this.idItem);
    }
  }

  carregarItem(id: number): void {
    this.carregandoItem.set(true);

    this.itemService.buscarPorId(id).subscribe({
      next: (item: ItemPerdido) => {
        this.formulario.patchValue({
          nomeItem: item.nomeItem,
          localEncontrado: item.localEncontrado,
          dataAchado: item.dataAchado,
          descricaoDevolucao: item.descricaoDevolucao
        });

        this.carregandoItem.set(false);
      },
      error: () => {
        this.mensagemErro.set('Não foi possível carregar o item.');
        this.carregandoItem.set(false);
      }
    });
  }

  salvar(): void {
    if (this.carregandoItem() || this.enviandoFormulario()) {
      return;
    }

    this.mensagemErro.set('');
    this.mensagemSucesso.set('');

    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    const dados: NovoItemPerdido = this.formulario.value;

    this.enviandoFormulario.set(true);

    if (this.idItem !== null) {

      this.itemService.editarItem(this.idItem, {
        ...dados,
        id: this.idItem
      } as ItemPerdido).subscribe({
        next: () => {
          this.enviandoFormulario.set(false);
          this.mensagemSucesso.set('Item atualizado com sucesso!');
          this.mostrarAviso.set(true);
        },
        error: () => {
          this.enviandoFormulario.set(false);
          this.mensagemErro.set('Não foi possível atualizar o item.');
        }
      });

    } else {

      this.itemService.cadastrarItem(dados).subscribe({
        next: () => {
          this.enviandoFormulario.set(false);
          this.mensagemSucesso.set('Item cadastrado com sucesso!');
          this.mostrarAviso.set(true);
        },
        error: () => {
          this.enviandoFormulario.set(false);
          this.mensagemErro.set('Não foi possível cadastrar o item.');
        }
      });
    }
  }

  fecharAviso(): void {
    this.mostrarAviso.set(false);
    this.router.navigate(['/']);
  }

}
