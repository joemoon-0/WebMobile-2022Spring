import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeSetterComponent } from './time-setter.component';

describe('TimeSetterComponent', () => {
  let component: TimeSetterComponent;
  let fixture: ComponentFixture<TimeSetterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimeSetterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TimeSetterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
