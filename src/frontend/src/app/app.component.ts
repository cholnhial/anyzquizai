import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterModule, RouterOutlet} from '@angular/router';
import {CommonModule, NgClass} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgClass, CommonModule, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'frontend';
  isDiscoverPage = false;
  constructor(private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.route.url.subscribe(url => {
      this.isDiscoverPage = url.join("/") == "/discover";
    });
  }
}
