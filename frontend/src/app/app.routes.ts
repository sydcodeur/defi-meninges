import { isDevMode } from '@angular/core';
import { Routes } from '@angular/router';

const devRoutes: Routes = isDevMode()
  ? [
    {
      path: 'sandbox',
      loadComponent: () => import('./features/sandbox/sandbox.component').then(m => m.SandboxComponent)
    }
  ]
  : [];

export const routes: Routes = [
  ...devRoutes,
  {
    path: '',
    loadComponent: () => import('./features/home/home').then(m => m.HomeComponent)
  },
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/register/register.component').then(m => m.RegisterComponent)
  },
  {
    path: 'lobby',
    loadComponent: () => import('./features/lobby/lobby.component').then(m => m.LobbyComponent)
  },
  {
    path: '**',
    redirectTo: ''
  }
];
