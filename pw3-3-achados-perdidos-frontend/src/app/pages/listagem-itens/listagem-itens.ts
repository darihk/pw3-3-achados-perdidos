import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemPerdidoService } from '../../services/item-perdido-service';
import { ItemPerdido, StatusItem } from '../../models/item-perdido';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-listagem-itens',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './listagem-itens.html',
  styleUrl: './listagem-itens.css'
})
export class ListagemItens implements OnInit {

  itens = signal<ItemPerdido[]>([]);
  mensagemErro = signal('');

  statusSelecionado = signal<'PERDIDO' | null>(null);

  constructor(private itemService: ItemPerdidoService) { }

  ngOnInit(): void {
    this.listarItens();
  }

  listarItens(): void {
    this.itemService.listarItens(
      this.statusSelecionado() ?? undefined
    ).subscribe({
      next: (dados) => {
        this.itens.set(dados);
        this.mensagemErro.set('');
      },
      error: () => {
        this.mensagemErro.set('Não foi possível carregar os itens.');
      }
    });
  }

  filtrarPorStatus(status: 'PERDIDO' | null): void {
    this.statusSelecionado.set(status);
    this.listarItens();
  }

  excluirItem(id: number): void {
    const confirmar = confirm('Deseja realmente excluir este item?');

    if (confirmar) {
      this.mensagemErro.set('');

      this.itemService.excluirItem(id).subscribe({
        next: () => {
          this.listarItens();
        },
        error: (erro) => {
          this.mensagemErro.set(
            erro.error?.mensagem || 'Não foi possível excluir o item.'
          );
        }
      });
    }
  }

  atualizarStatus(item: ItemPerdido): void {
    let novoStatus: StatusItem;

    if (item.statusItem === 'PERDIDO') {
      novoStatus = 'REIVINDICADO';
    } else if (item.statusItem === 'REIVINDICADO') {
      novoStatus = 'DEVOLVIDO';
    } else {
      return;
    }

    this.mensagemErro.set('');

    this.itemService.atualizarStatus(item.id, novoStatus).subscribe({
      next: () => {
        this.listarItens();
      },
      error: (erro) => {
        this.mensagemErro.set(
          erro.error?.mensagem || 'Não foi possível atualizar o status.'
        );
      }
    });
  }
}