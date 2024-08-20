import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule, NgClass } from '@angular/common';
import { Title } from '@angular/platform-browser';
import { filter, map } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgClass, CommonModule, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'frontend';
  isDiscoverPage = false;

  constructor(private route: ActivatedRoute,
              private titleService: Title,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.url.subscribe(url => {
      this.isDiscoverPage = url.join('/') == '/discover';
    });

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let route = this.route;
        while (route.firstChild) route = route.firstChild;
        return route;
      }),
      filter(route => route.outlet === 'primary'),
      map(route => route.snapshot.data['title']),
    ).subscribe((title: string) => {
      if (title) {
        this.titleService.setTitle(`AnyQuizAI | ${title}`);
      }
    });
  }
}
