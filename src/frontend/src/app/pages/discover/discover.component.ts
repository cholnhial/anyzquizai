import {Component, OnInit} from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {ICategory} from "../../models/category.model";
import {HttpResponse} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {IQuiz} from "../../models/quiz.model";

@Component({
  selector: 'app-discover',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './discover.component.html',
  styleUrl: './discover.component.scss'
})
export class DiscoverComponent implements OnInit {

  categories: ICategory[] = [];
  quizzes: IQuiz[] = [];

  constructor(private quizService: QuizService) {
  }


  ngOnInit(): void {
    this.quizService.getAllCategories().subscribe({
      next: (resp: HttpResponse<ICategory[]>) => {
        this.categories = resp.body || [];
      }
    });

    this.quizService.search({page: 1}).subscribe({
      next: (resp: HttpResponse<any>) => {
        this.quizzes = resp.body.content || [];
      }
    })
  }

}
