import { TestBed } from '@angular/core/testing';

import { ItemPerdidoService } from './item-perdido-service';

describe('ItemPerdido', () => {
  let service: ItemPerdidoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemPerdidoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
