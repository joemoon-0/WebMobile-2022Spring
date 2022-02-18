import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { TasksComponent } from './components/tasks/tasks.component';
import { TaskInputComponent } from './components/task-input/task-input.component';
import { ClockComponent } from './components/clock/clock.component';

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    TaskInputComponent,
    ClockComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
