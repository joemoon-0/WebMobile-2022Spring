import { Component } from '@angular/core';
import { TaskInterface } from './Task';
import { TimerInterface } from './Timer';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  // task data
  taskId: number = 0;

  // array for task objects
  items = [];

  // timer data
  userDate: TimerInterface;

  // Write code to push new item
  submitNewItem(newTask: TaskInterface) {
    // Assign ID to new task
    newTask.id = this.taskId;
    this.taskId++;

    // Add new task to array
    this.items.push(newTask);
  }

  // Write code to complete item
  completeItem(item: TaskInterface) {
    item.completed = !item.completed;
  }

  // Write code to delete item
  deleteItem(item: TaskInterface) {
    const index = this.items.indexOf(item);
    this.items.splice(index, 1);
  }
  
  startClock(newTimer: TimerInterface) {
    this.userDate = newTimer;
    this.clearClock();
  }

  clearClock() {
    document.getElementById("time-set").classList.toggle('hide');
    document.getElementById("countdownTimer").classList.toggle('hide');
  }
}
