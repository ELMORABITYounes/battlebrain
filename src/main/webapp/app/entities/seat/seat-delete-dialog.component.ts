import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISeat } from 'app/shared/model/seat.model';
import { SeatService } from './seat.service';

@Component({
  templateUrl: './seat-delete-dialog.component.html',
})
export class SeatDeleteDialogComponent {
  seat?: ISeat;

  constructor(protected seatService: SeatService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.seatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('seatListModification');
      this.activeModal.close();
    });
  }
}
