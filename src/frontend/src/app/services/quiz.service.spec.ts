import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {QuizService} from './quiz.service';
import {API_BASEURL} from '../app.constants';
import {IQuizFull} from "../models/quiz-full.model";
import {INewQuiz} from "../models/new-quiz.model";
import {IScore} from "../models/score.model";
import {IScoreSubmission} from "../models/score-submission.model";
import {ICategory} from "../models/category.model";


describe('QuizService', () => {
  let service: QuizService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [QuizService]
    });
    service = TestBed.inject(QuizService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  describe('search', () => {
    it('should send a GET request with search options and return paginated results', () => {
      const mockQuizzes: IQuizFull[] = [
        {
          id: 1,
          title: 'History Quiz',
          numberOfQuestions: 10,
          difficulty: 'medium',
          categoryId: 1,
          questions: [],
          created: new Date()
        },
        {
          id: 2,
          title: 'Science Quiz',
          numberOfQuestions: 15,
          difficulty: 'hard',
          categoryId: 2,
          questions: [],
          created: new Date()
        }
      ];

      const mockResponse = {
        content: mockQuizzes,
        first: true,
        last: false,
        totalPages: 5,
        totalElements: 50,
        number: 0
      };

      const searchOptions = {
        page: 0,
        size: 10,
        title: 'quiz',
        categoryId: '1',
        difficulty: 'medium',
        questions: '10',
        sort_created: 'desc'
      };

      service.search(searchOptions).subscribe(response => {
        expect(response.body).toEqual(mockResponse);
      });

      const req = httpMock.expectOne(`${API_BASEURL}/quiz?page=0&size=10&title=quiz&categoryId=1&difficulty=medium&questions=10&sort_created=desc`);
      expect(req.request.method).toBe('GET');
      req.flush(mockResponse);
    });
  });

  describe('getAllCategories', () => {
    it('should return an array of categories', () => {
      const mockCategories: ICategory[] = [
        { id: 1, name: 'History' },
        { id: 2, name: 'Science' }
      ];

      service.getAllCategories().subscribe(response => {
        expect(response.body).toEqual(mockCategories);
      });

      const req = httpMock.expectOne(`${API_BASEURL}/quiz/categories`);
      expect(req.request.method).toBe('GET');
      req.flush(mockCategories);
    });
  });

  describe('getQuizById', () => {
    it('should return a quiz by id', () => {
      const mockQuiz: IQuizFull = {
        id: 1,
        title: 'History Quiz',
        numberOfQuestions: 10,
        difficulty: 'medium',
        categoryId: 1,
        questions: [
          {
            question: 'Who was the first president of the United States?',
            correctAnswerLetter: 'A',
            answers: [
              { answerLetter: 'A', answerTitle: 'George Washington' },
              { answerLetter: 'B', answerTitle: 'Thomas Jefferson' },
              { answerLetter: 'C', answerTitle: 'John Adams' },
              { answerLetter: 'D', answerTitle: 'Benjamin Franklin' }
            ]
          }
        ],
        created: new Date()
      };

      service.getQuizById(1).subscribe(response => {
        expect(response.body).toEqual(mockQuiz);
      });

      const req = httpMock.expectOne(`${API_BASEURL}/quiz/1`);
      expect(req.request.method).toBe('GET');
      req.flush(mockQuiz);
    });
  });

  describe('create', () => {
    it('should create a new quiz', () => {
      const newQuiz: INewQuiz = {
        topic: 'New History Quiz',
        difficulty: 'easy',
        numberOfQuestions: 5,
        categoryId: 1
      };

      const createdQuiz: IQuizFull = {
        id: 1,
        title: 'New History Quiz',
        numberOfQuestions: 5,
        difficulty: 'easy',
        categoryId: 1,
        questions: [],
        created: new Date()
      };

      service.create(newQuiz).subscribe(response => {
        expect(response.body).toEqual(createdQuiz);
      });

      const req = httpMock.expectOne(`${API_BASEURL}/quiz`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(newQuiz);
      req.flush(createdQuiz);
    });
  });

  describe('getQuizScoresById', () => {
    it('should return scores for a quiz', () => {
      const mockScores: IScore[] = [
        { nickname: 'player1', totalCorrect: 8, totalQuestions: 10, score: 80, countryCode: 'US' },
        { nickname: 'player2', totalCorrect: 9, totalQuestions: 10, score: 90, countryCode: 'UK' }
      ];

      service.getQuizScoresById(1).subscribe(response => {
        expect(response.body).toEqual(mockScores);
      });

      const req = httpMock.expectOne(`${API_BASEURL}/quiz/1/score`);
      expect(req.request.method).toBe('GET');
      req.flush(mockScores);
    });
  });

  describe('submitScore', () => {
    it('should submit a new score', () => {
      const scoreSubmission: IScoreSubmission = { quizId: 1, totalCorrect: 5, nickname: 'joe', countryCode: 'AU' };
      const submittedScore: IScore = {
        nickname: 'player3',
        totalCorrect: 17,
        totalQuestions: 20,
        score: 85,
        countryCode: 'CA'
      };

      service.submitScore(scoreSubmission).subscribe(response => {
        expect(response.body).toEqual(submittedScore);
      });

      const req = httpMock.expectOne(`${API_BASEURL}/quiz/score`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(scoreSubmission);
      req.flush(submittedScore);
    });
  });
});
