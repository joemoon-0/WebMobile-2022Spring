import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { TimerInterface } from 'src/app/Timer';

@Component({
  selector: 'app-time-setter',
  templateUrl: './time-setter.component.html',
  styleUrls: ['./time-setter.component.css']
})
export class TimeSetterComponent implements OnInit {
  @Output() addTimerEvent: EventEmitter<TimerInterface> = new EventEmitter();
  time: string;
  date: string;
  minDate: string;
  today: Date = new Date();

  constructor() { }

  ngOnInit(): void {
    // sets minimum date value to yesterday
    this.minDate = (new Date(this.today.setDate(this.today.getDate()-1))).toISOString().slice(0, 10);
  }

  newTimer(date: any, time: any) {
    // Construct user specified date into standard format
    const userDate = new Date(`${date} ${time}:00`);

    const newTimer: TimerInterface = {
      years: userDate.getFullYear(),
      months: userDate.getMonth(),
      days: userDate.getDate(),
      hours: userDate.getHours(),
      minutes: userDate.getMinutes(),
      seconds: userDate.getSeconds()
    }
    this.addTimerEvent.emit(newTimer);
  }
  
}
