import {Component} from '@angular/core';
import {ActivatedRoute, RouterOutlet} from '@angular/router';
import {initFlowbite} from "flowbite";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgClass],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  isDiscoverPage = false;
  constructor(private route: ActivatedRoute) {
  }
  ngOnInit(): void {
    initFlowbite();
    this.route.url.subscribe(url => {
      this.isDiscoverPage = url.join("/") == "/discover";
      console.log("URL:  ", url.join("/"));
    })
  }
}
