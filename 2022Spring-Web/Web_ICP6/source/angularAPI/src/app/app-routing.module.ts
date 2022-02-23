import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipeSearchComponent } from './components/recipe-search/recipe-search.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'recipes', component: RecipeSearchComponent },
  { path: '**', component: PageNotFoundComponent }      // Wildcard Route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ RecipeSearchComponent, HomeComponent, PageNotFoundComponent ]