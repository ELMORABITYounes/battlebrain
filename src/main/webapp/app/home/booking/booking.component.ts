import { Component, OnInit } from '@angular/core';
import { ISeat } from '../../shared/model/seat.model';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { HttpResponse } from '@angular/common/http';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { Observable } from 'rxjs';
import { IArea } from '../../shared/model/area.model';
import { AreaService } from '../../entities/area/area.service';
import { BookingService } from './booking.service';

@Component({
  selector: 'jhi-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss'],
})
export class BookingComponent implements OnInit {
  isSaving = false;
  areas: IArea[] = [];
  bookingForm = this.fb.group({
    id: [],
    startDate: [null, [Validators.required]],
    endDate: [null, [Validators.required]],
    areaId: [null],
  });

  bookingInfos: any;

  constructor(
    protected bookingService: BookingService,
    protected areaService: AreaService,
    protected activatedRoute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.areaService.query().subscribe((res: HttpResponse<IArea[]>) => (this.areas = res.body || []));
  }

  previousState(): void {
    window.history.back();
  }

  submit(): void {
    this.isSaving = true;
    this.bookingInfos = {
      startDate: this.bookingForm.get(['startDate'])!.value
        ? moment(this.bookingForm.get(['startDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      endDate: this.bookingForm.get(['endDate'])!.value ? moment(this.bookingForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      areaId: this.bookingForm.get(['areaId'])!.value,
    };
    this.subscribeToSaveResponse(this.bookingService.getAvailableSeats(this.bookingInfos));
  }

  protected subscribeToSaveResponse(result: Observable<ISeat[]>): void {
    result.subscribe(
      availableSeats => this.onSaveSuccess(availableSeats),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(availableSeats: ISeat[]): void {
    this.bookingService.availableSeats = availableSeats;
    this.bookingService.bookingForm = this.bookingInfos;
    this.router.navigate(['booking/available-seats']);
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IArea): any {
    return item.id;
  }
}
