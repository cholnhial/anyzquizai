import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateComponent } from './create.component';
import { QuizService } from '../../services/quiz.service';
import { ToastrService } from 'ngx-toastr';
import { of, throwError } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { IQuizFull } from '../../models/quiz-full.model';
import { IAnswer } from '../../models/answer.model';
import { IQuestion } from '../../models/question.model';

describe('CreateComponent', () => {
  let component: CreateComponent;
  let fixture: ComponentFixture<CreateComponent>;
  let quizServiceSpy: jasmine.SpyObj<QuizService>;
  let toastrServiceSpy: jasmine.SpyObj<ToastrService>;

  beforeEach(async () => {
    const quizSpy = jasmine.createSpyObj('QuizService', ['getAllCategories', 'create']);
    const toastrSpy = jasmine.createSpyObj('ToastrService', ['success', 'error']);

    await TestBed.configureTestingModule({
      imports: [CreateComponent, FormsModule],
      providers: [
        { provide: QuizService, useValue: quizSpy },
        { provide: ToastrService, useValue: toastrSpy },
      ],
    }).compileComponents();

    quizServiceSpy = TestBed.inject(QuizService) as jasmine.SpyObj<QuizService>;
    toastrServiceSpy = TestBed.inject(ToastrService) as jasmine.SpyObj<ToastrService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set categories on init', () => {
    const mockCategories = [{ id: 1, name: 'Category 1' }, { id: 2, name: 'Category 2' }];
    quizServiceSpy.getAllCategories.and.returnValue(of(new HttpResponse({ body: mockCategories })));

    fixture.detectChanges();

    expect(component.categories).toEqual(mockCategories);
  });

  it('should create a quiz successfully', () => {
    const mockQuizFull: IQuizFull = {
      id: 1,
      title: 'Test Quiz',
      numberOfQuestions: 5,
      difficulty: 'MEDIUM',
      categoryId: 18,
      created: new Date(),
      questions: [
        createMockQuestion('Question 1', 'A'),
        createMockQuestion('Question 2', 'B'),
        createMockQuestion('Question 3', 'C'),
        createMockQuestion('Question 4', 'D'),
        createMockQuestion('Question 5', 'A'),
      ],
    };

    quizServiceSpy.create.and.returnValue(of(new HttpResponse({ body: mockQuizFull })));

    component.onCreate();

    expect(component.quizId).toBe(1);
    expect(component.success).toBeTrue();
    expect(component.error).toBeFalse();
    expect(component.creating).toBeTrue();
  });

  it('should handle quiz creation error', () => {
    quizServiceSpy.create.and.returnValue(throwError(() => new Error('Error')));

    component.onCreate();

    expect(component.error).toBeTrue();
    expect(component.creating).toBeFalse();
    expect(component.success).toBeFalse();
  });

  it('should reset flags on try again', () => {
    component.success = true;
    component.error = true;
    component.creating = true;

    component.onTryAgain();

    expect(component.success).toBeFalse();
    expect(component.error).toBeFalse();
    expect(component.creating).toBeFalse();
  });

  it('should initialize with default quiz values', () => {
    expect(component.quiz).toEqual({
      topic: '',
      categoryId: 18,
      difficulty: 'EASY',
      numberOfQuestions: 10,
    });
  });

  it('should have correct nquestions and difficulties', () => {
    expect(component.nquestions).toEqual([5, 10, 20, 30]);
    expect(component.difficulties).toEqual(['EASY', 'MEDIUM', 'HARD']);
  });
});

function createMockQuestion(questionText: string, correctAnswer: string): IQuestion {
  const answers: IAnswer[] = [
    { answerLetter: 'A', answerTitle: 'Answer A' },
    { answerLetter: 'B', answerTitle: 'Answer B' },
    { answerLetter: 'C', answerTitle: 'Answer C' },
    { answerLetter: 'D', answerTitle: 'Answer D' },
  ];

  return {
    question: questionText,
    correctAnswerLetter: correctAnswer,
    answers: answers,
  };
}
