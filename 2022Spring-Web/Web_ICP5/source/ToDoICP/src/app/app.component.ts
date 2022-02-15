import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // task data
  task: String = "";
  completed: boolean = false;

  // define list of items
  items= [];

  // Write code to push new item
  submitNewItem(task) {
    this.items.push(task);
  }

  // Write code to complete item
  completeItem(task) {
    console.log(task);
    console.log()
  }

  // Write code to delete item
  deleteItem(task) {
    const index = this.items.indexOf(task);
    this.items.splice(index, 1);
  }

}
