import {Component} from '@angular/core';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-play',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './play.component.html',
  styleUrl: './play.component.scss'
})
export class PlayComponent {
  activeTab: string = 'play';


  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }
}
