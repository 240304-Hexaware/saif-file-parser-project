import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatFileCollectionComponent } from './flat-file-collection.component';

describe('FlatFileCollectionComponent', () => {
  let component: FlatFileCollectionComponent;
  let fixture: ComponentFixture<FlatFileCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlatFileCollectionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FlatFileCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
