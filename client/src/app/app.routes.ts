import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ParserComponent } from './parser/parser.component';
import { FlatFileCollectionComponent } from './flat-file-collection/flat-file-collection.component';
import { SpecFileCollectionComponent } from './spec-file-collection/spec-file-collection.component';
import { ResultDisplayComponent } from './result-display/result-display.component';
import { RecordsListComponent } from './records-list/records-list.component';
import { RecordComponent } from './record/record.component';
import { FieldComponent } from './field/field.component';
import { RegistrationComponent } from './registration/registration.component';


export const routes: Routes = [
    { path: "", redirectTo: "login", pathMatch: "full"},
    { path: "login", component: LoginComponent},
    { path: "registration", component: RegistrationComponent},
    { path: "dashboard", component: DashboardComponent, 
        children:[
            {path: "", redirectTo: "parser", pathMatch: "full" },
            {path: "specifications" , component: SpecFileCollectionComponent},
            {path: "flat-files" , component: FlatFileCollectionComponent},
            {path: "parser", component: ParserComponent, 
                children:[
                    { path: "parser", component: ResultDisplayComponent }
                ]},
            {path: "records" , component: RecordsListComponent,
                children:[
                    { path: "records", component: RecordComponent, 
                        children:[
                            { path: "records", component: FieldComponent}
                    ] }
                ]},           
        ]
    },
];
