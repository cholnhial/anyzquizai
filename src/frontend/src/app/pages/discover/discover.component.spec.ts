import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { DiscoverComponent } from './discover.component';
import { QuizService } from '../../services/quiz.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { ICategory } from '../../models/category.model';
import { IQuiz } from '../../models/quiz.model';

describe('DiscoverComponent', () => {
  let component: DiscoverComponent;
  let fixture: ComponentFixture<DiscoverComponent>;
  let quizServiceSpy: jasmine.SpyObj<QuizService>;
  let toastrServiceSpy: jasmine.SpyObj<ToastrService>;
  let routerSpy: jasmine.SpyObj<Router>;
  let activatedRouteMock: Partial<ActivatedRoute>;

  const mockCategories: ICategory[] = [
    { id: 1, name: 'Category 1' },
    { id: 2, name: 'Category 2' },
  ];

  const mockQuizzes: IQuiz[] = [
    {
      id: 1,
      title: 'Quiz 1',
      questions: 10,
      uniqueCode: 'ABC123',
      difficulty: 'Easy',
      categoryId: 1,
      created: new Date(),
    },
    {
      id: 2,
      title: 'Quiz 2',
      questions: 15,
      uniqueCode: 'DEF456',
      difficulty: 'Medium',
      categoryId: 2,
      created: new Date(),
    },
  ];

  const mockHttpResponse = new HttpResponse({
    body: {
      content: mockQuizzes,
      first: true,
      last: true,
      totalPages: 1,
      totalElements: 2,
      number: 0,
    },
  });

  beforeEach(async () => {
    quizServiceSpy = jasmine.createSpyObj('QuizService', ['getAllCategories', 'search']);
    toastrServiceSpy = jasmine.createSpyObj('ToastrService', ['success', 'error']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);
    activatedRouteMock = {
      queryParams: of({}),
    };

    await TestBed.configureTestingModule({
      imports: [DiscoverComponent],
      providers: [
        { provide: QuizService, useValue: quizServiceSpy },
        { provide: ToastrService, useValue: toastrServiceSpy },
        { provide: Router, useValue: routerSpy },
        { provide: ActivatedRoute, useValue: activatedRouteMock },
      ],
    }).compileComponents();

    quizServiceSpy.getAllCategories.and.returnValue(of(new HttpResponse({ body: mockCategories })));
    quizServiceSpy.search.and.returnValue(of(mockHttpResponse));

    fixture = TestBed.createComponent(DiscoverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should load categories and quizzes', () => {
      expect(quizServiceSpy.getAllCategories).toHaveBeenCalled();
      expect(quizServiceSpy.search).toHaveBeenCalled();
      expect(component.categories).toEqual(mockCategories);
      expect(component.quizzes).toEqual(mockQuizzes);
    });
  });

  describe('loadQuizzes', () => {
    it('should update component properties based on API response', () => {
      component.loadQuizzes();
      expect(component.isFirstPage).toBeTrue();
      expect(component.isLastPage).toBeTrue();
      expect(component.totalPages).toBe(1);
      expect(component.totalElements).toBe(2);
      expect(component.currentPage).toBe(1);
      expect(component.isLoading).toBeFalse();
    });

    it('should update URL with search params', fakeAsync(() => {
      component.loadQuizzes();
      tick();
      expect(routerSpy.navigate).toHaveBeenCalled();
    }));
  });

  describe('pagination', () => {
    it('should go to the previous page', () => {
      component.currentPage = 2;
      component.goToPrevPage(new Event('click'));
      expect(component.currentPage).toBe(1);
      expect(quizServiceSpy.search).toHaveBeenCalled();
    });


  });

  describe('search and sorting', () => {
    it('should clear title', () => {
      component.searchOptions.title = 'Test';
      component.onClearTitle();
      expect(component.searchOptions.title).toBe('');
    });

    it('should sort by difficulty', () => {
      component.onSortDifficulty('DESC', new Event('click'));
      expect(component.searchOptions.sort_difficulty).toBe('DESC');
      expect(component.searchOptions.sort_created).toBeUndefined();
      expect(quizServiceSpy.search).toHaveBeenCalled();
    });

    it('should sort by creation date', () => {
      component.onSortCreated('ASC', new Event('click'));
      expect(component.searchOptions.sort_created).toBe('ASC');
      expect(component.searchOptions.sort_difficulty).toBeUndefined();
      expect(quizServiceSpy.search).toHaveBeenCalled();
    });
  });

  describe('utility functions', () => {
    it('should get category name by id', () => {
      const categoryName = component.getCategoryNameById(1);
      expect(categoryName).toBe('Category 1');
    });

    it('should compute search params correctly', () => {
      component.searchOptions = {
        categoryId: '1',
        difficulty: 'Easy',
        page: 1,
        questions: '10',
        size: 6,
        title: 'Test Quiz',
        sort_difficulty: 'ASC',
      };
      const params = component.computeSearchParams();
      expect(params).toEqual(component.searchOptions);
    });
  });
});
