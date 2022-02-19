import { Component, Input, OnInit, Output , EventEmitter} from '@angular/core';
import { TimerInterface } from 'src/app/Timer';

@Component({
  selector: 'app-clock',
  templateUrl: './clock.component.html',
  styleUrls: ['./clock.component.css']
})
export class ClockComponent implements OnInit {
  @Input() userDate: TimerInterface;
  @Output() clearTimerEvent: EventEmitter<void> = new EventEmitter();


  years: number;
  months: number;
  days: number;
  hours: number;
  minutes: number;
  seconds: number;

  constructor() { }

  ngOnInit(): void {
  }

  countdown = setInterval(() => {
    let targetDate = new Date(
      this.userDate.years,
      this.userDate.months,
      this.userDate.days,
      this.userDate.hours,
      this.userDate.minutes,
      this.userDate.seconds,
      ).getTime();
    let currentTime = new Date().getTime();
    let difference = targetDate - currentTime;

    this.years = Math.floor(difference / (1000 * 60 * 60 * 24 * 365))
    this.months = Math.floor(difference / (1000 * 60 * 60 * 24 * 12))
    this.days = Math.floor(difference / (1000 * 60 * 60 * 24));
    this.hours = Math.floor((difference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    this.minutes = Math.floor((difference % (1000 * 60 * 60)) / (1000 * 60));
    this.seconds = Math.floor((difference % (1000 * 60)) / (1000));

  }, 1000);

  clearTimer() {
    this.clearTimerEvent.emit();  
  }
}
