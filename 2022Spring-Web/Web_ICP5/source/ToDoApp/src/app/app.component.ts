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
  description: string;

  // define list of items
  items= [];

  // Write code to push new item
  submitNewItem(description: string) {
    console.log("submit");
    this.task.id = this.items.length;
    this.task.description = description;
    this.task.completed = false;
    this.items.push(this.task);
  }

  // Write code to complete item
  completeItem(item) {
    
    console.log("completeItem");
  }

  // Write code to delete item
  deleteItem() {
    // const index = this.items.indexOf(task);
    // this.items.splice(index, 1);
  }

}
