import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Discover',
    },
    loadComponent: () => import('./pages/discover/discover.component').then(m => m.DiscoverComponent),
  },
  {
    path: 'create',
    data: {
      title: 'Create',
    },
    loadComponent: () => import('./pages/create/create.component').then(m => m.CreateComponent),
  },
  {
    path: 'play/:quizId',
    data: {
      title: 'Play',
    },
    loadComponent: () => import('./pages/play/play.component').then(m => m.PlayComponent),
  },
];
