import {Component, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {IQuizFull} from "../../models/quiz-full.model";
import {QuizService} from "../../services/quiz.service";

@Component({
  selector: 'app-play',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './play.component.html',
  styleUrl: './play.component.scss'
})
export class PlayComponent implements OnInit{

  activeTab: string = 'play';
  quiz?: IQuizFull;
  currentQuestionIndex = 0;
  nextQuestionIsReady = false;
  isAnswerWrong = false;
  isAnswerCorrect = false;

  successMessages = [
    "You're killing it!",
    "No doubt about you mate!",
    "You are the master",
    "This is a walk in the park for you isn't it?",
    "Mate leave some for us",
    "Stunning!"
  ];

  wrongMessages = [
    "We will get them next time",
    "Even ChatGPT is better than you",
    "Do you need your mummy",
    "Come on you can do this!",
    "Never give up!",
    "Giving up is not an option"
  ]

  constructor(private route: ActivatedRoute, private quizService: QuizService) {
  }

  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number.parseInt(params.get('quizId') || '0');
      this.loadQuiz(id);
    });
  }

  loadQuiz(id: number) {
    this.quizService.getQuizById(id).subscribe({
      next: resp => {
        this.quiz = resp.body!!;
      },
      error: err => {
        //TODO: handle error show 404 or something
      }
    })
  }

  getQuizAnswersForCurrentQuestionSorted() {
     return this.quiz?.questions[this.currentQuestionIndex]
       .answers.
       sort((a, b) => (a.answerLetter > b.answerLetter)
         ? 1 : ((b.answerLetter > a.answerLetter) ? -1 : 0));
  }

  getCurrentQuestion() {
    return this.quiz?.questions[this.currentQuestionIndex].question;
  }

  getCurrentQuestionCorrectAnswerLetter() {
    return this.quiz?.questions[this.currentQuestionIndex].correctAnswerLetter;
  }

  onAnswer(answerLetter: string) {
    if (this.quiz?.questions[this.currentQuestionIndex].correctAnswerLetter == answerLetter) {
      this.isAnswerCorrect = true;
    } else {
      this.isAnswerWrong = true;
    }
    this.nextQuestionIsReady = true;
  }

  getRandomMessage(messages: string[]) {
    const randomIndex = Math.floor(Math.random() * messages.length);
    // Return the message at the random index
    return messages[randomIndex];
  }

  resetForNextQuestion() {
    this.isAnswerWrong = false;
    this.isAnswerCorrect = false;
    this.nextQuestionIsReady = false;
  }
  onNext() {
    this.resetForNextQuestion();
    this.currentQuestionIndex++;
  }

}
