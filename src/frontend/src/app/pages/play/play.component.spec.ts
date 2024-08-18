import {ComponentFixture, TestBed} from '@angular/core/testing';
import {PlayComponent} from './play.component';
import {ActivatedRoute} from '@angular/router';
import {QuizService} from '../../services/quiz.service';
import {ToastrService} from 'ngx-toastr';
import {of, throwError} from 'rxjs';
import {HttpResponse} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {IScore} from '../../models/score.model';
import {IQuizFull} from "../../models/quiz-full.model";

describe('PlayComponent', () => {
  let component: PlayComponent;
  let fixture: ComponentFixture<PlayComponent>;
  let quizServiceSpy: jasmine.SpyObj<QuizService>;
  let toastrServiceSpy: jasmine.SpyObj<ToastrService>;
  let activatedRouteSpy: jasmine.SpyObj<ActivatedRoute>;

  const mockQuizWithScores: { quiz: IQuizFull; scores: IScore[] } = {
    quiz: {
      id: 1,
      title: 'Test Quiz',
      numberOfQuestions: 2,
      difficulty: 'MEDIUM',
      categoryId: 18,
      created: new Date(),
      questions: [
        {
          question: 'What is 2 + 2?',
          correctAnswerLetter: 'B',
          answers: [
            { answerLetter: 'A', answerTitle: '3' },
            { answerLetter: 'B', answerTitle: '4' },
            { answerLetter: 'C', answerTitle: '5' },
            { answerLetter: 'D', answerTitle: '6' }
          ]
        },
        {
          question: 'What is the capital of France?',
          correctAnswerLetter: 'C',
          answers: [
            { answerLetter: 'A', answerTitle: 'London' },
            { answerLetter: 'B', answerTitle: 'Berlin' },
            { answerLetter: 'C', answerTitle: 'Paris' },
            { answerLetter: 'D', answerTitle: 'Madrid' }
          ]
        }
      ]
    },
    scores: [
      { nickname: 'Player1', totalCorrect: 2, totalQuestions: 2, score: 100, countryCode: 'us' },
      { nickname: 'Player2', totalCorrect: 1, totalQuestions: 2, score: 50, countryCode: 'uk' }
    ]
  };

  beforeEach(async () => {
    const quizSpy = jasmine.createSpyObj('QuizService', ['getQuizById', 'getQuizScoresById', 'submitScore']);
    const toastrSpy = jasmine.createSpyObj('ToastrService', ['success', 'error']);
    const routeSpy = jasmine.createSpyObj('ActivatedRoute', [], { paramMap: of({ get: () => '1' }) });

    await TestBed.configureTestingModule({
      imports: [PlayComponent, FormsModule],
      providers: [
        { provide: QuizService, useValue: quizSpy },
        { provide: ToastrService, useValue: toastrSpy },
        { provide: ActivatedRoute, useValue: routeSpy }
      ]
    }).compileComponents();

    quizServiceSpy = TestBed.inject(QuizService) as jasmine.SpyObj<QuizService>;
    toastrServiceSpy = TestBed.inject(ToastrService) as jasmine.SpyObj<ToastrService>;
    activatedRouteSpy = TestBed.inject(ActivatedRoute) as jasmine.SpyObj<ActivatedRoute>;
  });

  beforeEach(() => {
    quizServiceSpy.getQuizById.and.returnValue(of(new HttpResponse({ body: mockQuizWithScores.quiz })));
    quizServiceSpy.getQuizScoresById.and.returnValue(of(new HttpResponse({ body: mockQuizWithScores.scores })));
    fixture = TestBed.createComponent(PlayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load quiz and scores on init', () => {
    expect(component.quiz).toEqual(mockQuizWithScores.quiz);
    expect(component.quizScores).toEqual(mockQuizWithScores.scores);
    expect(component.totalQuestions).toBe(2);
    expect(component.isLoading).toBeFalse();
  });

  it('should get current question', () => {
    expect(component.getCurrentQuestion()).toBe('What is 2 + 2?');
  });

  it('should get current question correct answer letter', () => {
    expect(component.getCurrentQuestionCorrectAnswerLetter()).toBe('B');
  });

  it('should handle correct answer', () => {
    component.onAnswer('B');
    expect(component.isAnswerCorrect).toBeTrue();
    expect(component.totalCorrect).toBe(1);
    expect(component.nextQuestionIsReady).toBeTrue();
  });

  it('should handle wrong answer', () => {
    component.onAnswer('A');
    expect(component.isAnswerWrong).toBeTrue();
    expect(component.totalCorrect).toBe(0);
    expect(component.nextQuestionIsReady).toBeTrue();
  });

  it('should move to next question', () => {
    component.onNext();
    expect(component.currentQuestionIndex).toBe(1);
    expect(component.isAnswerWrong).toBeFalse();
    expect(component.isAnswerCorrect).toBeFalse();
    expect(component.nextQuestionIsReady).toBeFalse();
  });

  it('should complete quiz after last question', () => {
    component.currentQuestionIndex = 1;
    component.onAnswer('C');
    expect(component.complete).toBeTrue();
  });

  it('should submit score', () => {
    const mockNewScore: IScore = { nickname: 'NewPlayer', totalCorrect: 2, totalQuestions: 2, score: 100, countryCode: 'fr' };
    quizServiceSpy.submitScore.and.returnValue(of(new HttpResponse({ body: mockNewScore })));
    quizServiceSpy.getQuizScoresById.and.returnValue(of(new HttpResponse({ body: [...mockQuizWithScores.scores, mockNewScore] })));

    component.nickname = 'NewPlayer';
    component.selectedCountry = 'fr';
    component.totalCorrect = 2;
    component.onSubmitScore();

    expect(component.activeTab).toBe('leaderboard');
    expect(component.complete).toBeFalse();
    expect(component.quizScores.length).toBe(3);
    expect(component.currentQuestionIndex).toBe(0);
    expect(component.totalCorrect).toBe(0);
  });

  it('should handle error when loading quiz', () => {
    quizServiceSpy.getQuizById.and.throwError('Test error');
    component.ngOnInit();
    expect(toastrServiceSpy.error).toHaveBeenCalledWith('An error occurred while loading quiz and its scores', 'Error');
  });

  it('should handle error when submitting score', () => {
    quizServiceSpy.submitScore.and.returnValue(throwError(() => new Error('Test error')));
    component.onSubmitScore();
    expect(toastrServiceSpy.error).toHaveBeenCalledWith('Unable to submit your score', 'Error');
  });
});
