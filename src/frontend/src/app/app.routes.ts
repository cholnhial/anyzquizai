import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/discover/discover.component').then(m => m.DiscoverComponent)
  },
  {
    path: 'create',
    loadComponent: () => import('./pages/create/create.component').then(m => m.CreateComponent)
  },
  {
    path: 'play/:quizId',
    loadComponent: () => import('./pages/play/play.component').then( m => m.PlayComponent)
  }
];
