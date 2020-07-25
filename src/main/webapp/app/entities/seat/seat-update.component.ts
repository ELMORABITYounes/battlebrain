import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISeat, Seat } from 'app/shared/model/seat.model';
import { SeatService } from './seat.service';
import { IArea } from 'app/shared/model/area.model';
import { AreaService } from 'app/entities/area/area.service';

type SelectableEntity = ISeat | IArea;

@Component({
  selector: 'jhi-seat-update',
  templateUrl: './seat-update.component.html',
})
export class SeatUpdateComponent implements OnInit {
  isSaving = false;
  rightseats: ISeat[] = [];
  leftseats: ISeat[] = [];
  frontseats: ISeat[] = [];
  areas: IArea[] = [];

  editForm = this.fb.group({
    id: [],
    number: [null, [Validators.required]],
    status: [],
    aisleSeat: [],
    windowSeat: [],
    middleSeat: [],
    rightSeatId: [],
    leftSeatId: [],
    frontSeatId: [],
    areaId: [],
  });

  constructor(
    protected seatService: SeatService,
    protected areaService: AreaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ seat }) => {
      this.updateForm(seat);

      this.seatService
        .query({ filter: 'seat-is-null' })
        .pipe(
          map((res: HttpResponse<ISeat[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISeat[]) => {
          if (!seat.rightSeatId) {
            this.rightseats = resBody;
          } else {
            this.seatService
              .find(seat.rightSeatId)
              .pipe(
                map((subRes: HttpResponse<ISeat>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISeat[]) => (this.rightseats = concatRes));
          }
        });

      this.seatService
        .query({ filter: 'seat-is-null' })
        .pipe(
          map((res: HttpResponse<ISeat[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISeat[]) => {
          if (!seat.leftSeatId) {
            this.leftseats = resBody;
          } else {
            this.seatService
              .find(seat.leftSeatId)
              .pipe(
                map((subRes: HttpResponse<ISeat>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISeat[]) => (this.leftseats = concatRes));
          }
        });

      this.seatService
        .query({ filter: 'seat-is-null' })
        .pipe(
          map((res: HttpResponse<ISeat[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISeat[]) => {
          if (!seat.frontSeatId) {
            this.frontseats = resBody;
          } else {
            this.seatService
              .find(seat.frontSeatId)
              .pipe(
                map((subRes: HttpResponse<ISeat>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISeat[]) => (this.frontseats = concatRes));
          }
        });

      this.areaService.query().subscribe((res: HttpResponse<IArea[]>) => (this.areas = res.body || []));
    });
  }

  updateForm(seat: ISeat): void {
    this.editForm.patchValue({
      id: seat.id,
      number: seat.number,
      status: seat.status,
      aisleSeat: seat.aisleSeat,
      windowSeat: seat.windowSeat,
      middleSeat: seat.middleSeat,
      rightSeatId: seat.rightSeatId,
      leftSeatId: seat.leftSeatId,
      frontSeatId: seat.frontSeatId,
      areaId: seat.areaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const seat = this.createFromForm();
    if (seat.id !== undefined) {
      this.subscribeToSaveResponse(this.seatService.update(seat));
    } else {
      this.subscribeToSaveResponse(this.seatService.create(seat));
    }
  }

  private createFromForm(): ISeat {
    return {
      ...new Seat(),
      id: this.editForm.get(['id'])!.value,
      number: this.editForm.get(['number'])!.value,
      status: this.editForm.get(['status'])!.value,
      aisleSeat: this.editForm.get(['aisleSeat'])!.value,
      windowSeat: this.editForm.get(['windowSeat'])!.value,
      middleSeat: this.editForm.get(['middleSeat'])!.value,
      rightSeatId: this.editForm.get(['rightSeatId'])!.value,
      leftSeatId: this.editForm.get(['leftSeatId'])!.value,
      frontSeatId: this.editForm.get(['frontSeatId'])!.value,
      areaId: this.editForm.get(['areaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISeat>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
