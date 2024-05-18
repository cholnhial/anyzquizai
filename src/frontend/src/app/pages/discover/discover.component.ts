import {Component, OnInit} from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {ICategory} from "../../models/category.model";
import {HttpResponse} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {IQuiz} from "../../models/quiz.model";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-discover',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './discover.component.html',
  styleUrl: './discover.component.scss'
})
export class DiscoverComponent implements OnInit {

  categories: ICategory[] = [];
  quizzes: IQuiz[] = [];
  searchOptions = {
    page: 0,
    size: 6,
    title: '',
    categoryId: undefined,
    difficulty: undefined,
    questions: undefined,
    sort_created: 'DESC',
    sort_difficulty: 'ASC'}


  constructor(private quizService: QuizService) {
  }

  computeSearchParams() {
    return Object.entries(this.searchOptions)
      .filter(([key, value]) => !!value) // Filter out falsy values
      .reduce((acc, [key, value]) => {
        return {
          ...acc,
          [key]: value
        };
      }, {});
  }

  ngOnInit(): void {
    this.quizService.getAllCategories().subscribe({
      next: (resp: HttpResponse<ICategory[]>) => {
        this.categories = resp.body || [];
      }
    });

    this.loadQuizzes();

  }

  loadQuizzes() {
    this.quizService.search(this.computeSearchParams()).subscribe({
      next: (resp: HttpResponse<any>) => {
        this.quizzes = resp.body.content || [];
      }
    })
  }

  onSearch() {
    this.loadQuizzes();
  }

}
