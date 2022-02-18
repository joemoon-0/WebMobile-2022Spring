import { Component } from '@angular/core';
import { TaskInterface } from './Task';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  // task data
  task: TaskInterface;
  taskId: number = 0;

  // array for task objects
  items = [];

  // Write code to push new item
  submitNewItem(newTask: TaskInterface) {
    console.log("submitted");   
    
    // Assign ID to new task
    newTask.id = this.taskId;
    this.taskId++;

    this.items.push(newTask);
    console.log(newTask)
  }

  // Write code to complete item
  completeItem(item: TaskInterface) {
    item.completed = !item.completed;
  }

  // Write code to delete item
  deleteItem(item) {
    const index = this.items.indexOf(item);
    this.items.splice(index, 1);
    console.log(this.items);
  }
  

}
