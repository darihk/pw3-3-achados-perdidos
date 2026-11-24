import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ItemPerdido, NovoItemPerdido, StatusItem } from '../models/item-perdido';

@Injectable({
  providedIn: 'root'
})
export class ItemPerdidoService {

  private apiUrl = 'http://localhost:8080/api/itens';

  constructor(private http: HttpClient) { }

  listarItens(status?: StatusItem): Observable<ItemPerdido[]> {
    let params = new HttpParams();

    if (status) {
      params = params.set('status', status);
    }

    return this.http.get<ItemPerdido[]>(this.apiUrl, { params });
  }

  buscarPorId(id: number): Observable<ItemPerdido> {
    return this.http.get<ItemPerdido>(`${this.apiUrl}/${id}`);
  }

  cadastrarItem(item: NovoItemPerdido): Observable<ItemPerdido> {
    return this.http.post<ItemPerdido>(this.apiUrl, item);
  }

  editarItem(id: number, item: ItemPerdido): Observable<ItemPerdido> {
    return this.http.put<ItemPerdido>(`${this.apiUrl}/${id}`, item);
  }

  excluirItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  atualizarStatus(id: number, statusItem: StatusItem): Observable<ItemPerdido> {
    return this.http.patch<ItemPerdido>(
      `${this.apiUrl}/${id}/status`,
      { statusItem: statusItem }
    );
  }
}