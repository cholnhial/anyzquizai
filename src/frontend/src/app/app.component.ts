import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {initFlowbite} from "flowbite";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';

  ngOnInit(): void {
    initFlowbite();
  }
}
