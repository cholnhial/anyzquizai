import {Component, OnInit} from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {ICategory} from "../../models/category.model";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {INewQuiz} from "../../models/new-quiz.model";
import {RouterModule} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent implements OnInit {

  constructor(private quizService: QuizService,
              private toastr: ToastrService) {

  }

  error = false;
  success = false;
  quizId?: number;
  creating = false;
  nquestions = [5,10,20,30];
  difficulties = ['EASY', 'MEDIUM', 'HARD'];
  quiz: INewQuiz = {topic: '', categoryId: 18, difficulty: 'EASY', numberOfQuestions: 10}
  categories: ICategory[] = [];

  ngOnInit(): void {
    this.setCategories();
  }

  setCategories() {
    this.quizService.getAllCategories().subscribe({
      next: (resp) => {
        this.categories = resp.body || [];
      }
    })
  }


  onCreate() {
    this.creating = true;
    this.quizService.create(this.quiz).subscribe({
      next: resp => {
        this.quizId = resp.body?.id;
        this.success = true;
        this.error = false;
      },
      error: err => {
        this.error = true;
        this.creating = false;
        this.success = false;
      }
    })
  }

  onTryAgain() {
    this.success = false;
    this.error = false;
    this.creating = false;
  }
}
