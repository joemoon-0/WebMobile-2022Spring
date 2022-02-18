import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';
import { TaskInterface } from 'src/app/Task';

@Component({
  selector: 'app-task-input',
  templateUrl: './task-input.component.html',
  styleUrls: ['./task-input.component.css']
})
export class TaskInputComponent implements OnInit {
  @Input() text: string;
  @Output() addTaskEvent: EventEmitter<TaskInterface> = new EventEmitter();

  id: number = 0;
  description: string;
  completed: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  newItem(text: string) {
    const newTask: TaskInterface = {
      id: this.id,
      description: this.text,
      completed: this.completed
    }
    this.addTaskEvent.emit(newTask);
  }
}
