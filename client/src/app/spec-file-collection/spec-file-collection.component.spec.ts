import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecFileCollectionComponent } from './spec-file-collection.component';

describe('SpecFileCollectionComponent', () => {
  let component: SpecFileCollectionComponent;
  let fixture: ComponentFixture<SpecFileCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpecFileCollectionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SpecFileCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
