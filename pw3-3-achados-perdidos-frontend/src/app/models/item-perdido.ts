export type StatusItem =
  | 'PERDIDO'
  | 'REIVINDICADO'
  | 'DEVOLVIDO';

export interface ItemPerdido {
    id: number;
    nomeItem: string;
    localEncontrado: string;
    dataAchado: string;
    statusItem: StatusItem;
    descricaoDevolucao: string | null;
}

export interface NovoItemPerdido {
    nomeItem: string;
    localEncontrado: string;
    dataAchado: string;
    descricaoDevolucao: string | null;
}