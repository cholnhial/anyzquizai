import {Component, OnInit} from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {ICategory} from "../../models/category.model";
import {HttpResponse} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {IQuiz} from "../../models/quiz.model";
import {FormsModule} from "@angular/forms";
import {ActivatedRoute, NavigationExtras, Router, RouterModule} from "@angular/router";
import {initFlowbite} from "flowbite";
import {API_BASEURL} from "../../app.constants";

@Component({
  selector: 'app-discover',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './discover.component.html',
  styleUrl: './discover.component.scss'
})
export class DiscoverComponent implements OnInit {

  categories: ICategory[] = [];
  quizzes: IQuiz[] = [];
  isLastPage = false;
  isFirstPage = false;
  isLoading = true;
  totalPages: number = 0;
  totalElements: number = 0;
  currentPage: number = 0;

  searchOptions =  {
    page: 1,
    size: 6,
    title: '',
    categoryId: '',
    difficulty: '',
    questions: '',
    sort_created: 'DESC',
    sort_difficulty: 'ASC'
  }


  constructor(private quizService: QuizService,
              private router: Router,
              private route: ActivatedRoute, ) {
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

  mergeSearchOptionsWithRouteQueryParams() {
    this.route.queryParams.subscribe(params => {
      this.searchOptions = {...this.searchOptions, ...params};
    });
  }

  ngOnInit(): void {

    this.mergeSearchOptionsWithRouteQueryParams();

    this.quizService.getAllCategories().subscribe({
      next: (resp: HttpResponse<ICategory[]>) => {
        this.categories = resp.body || [];
      }
    });
    initFlowbite();
    this.loadQuizzes();

  }

  loadQuizzes() {
    const computedSearchParams = this.computeSearchParams();
    this.isLoading = true;
    this.quizService.search(computedSearchParams).subscribe({
      next: async (resp: HttpResponse<any>) => {
        this.quizzes = resp.body.content || [];
        this.isFirstPage = resp.body.first;
        this.isLastPage = resp.body.last;
        this.totalPages = resp.body.totalPages ?? 0;
        this.totalElements = resp.body.totalElements ?? 0;
        this.currentPage = resp.body.number + 1;
        this.isLoading = false;
        // Update URL (so it contains search params)
        const queryParams: NavigationExtras = { queryParams: computedSearchParams };
        await this.router.navigate([], queryParams);
      }
    })
  }

  goToPage(event: Event, pageNum: number) {
    event.preventDefault(); // Prevent the default link behavior
    // Handle navigation to the selected page
    this.currentPage = pageNum;
    this.searchOptions.page = pageNum;
    this.onSearch();
  }

  goToPrevPage(event: Event) {
    event.preventDefault(); // Prevent the default link behavior
    if (this.currentPage > 1) {
      this.currentPage--;
      this.searchOptions.page = this.currentPage;
      this.onSearch();
    }
  }

  goToNextPage(event: Event) {
    event.preventDefault(); // Prevent the default link behavior
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.searchOptions.page = this.currentPage;
      this.onSearch();
    }
  }

  getPageNumbers(maxPagesToShow = 5): number[] {
    const pageNumbers = [];
    let startPage = 1;
    let endPage = this.totalPages;

    // Always include the first and last pages
    pageNumbers.push(1);
    if (this.totalPages > 1) {
      pageNumbers.push(this.totalPages);
    }

    // Calculate the start and end pages based on the current page and maxPagesToShow
    if (this.totalPages > maxPagesToShow) {
      const maxPagesBeforeCurrent = Math.floor(maxPagesToShow / 2);
      const maxPagesAfterCurrent = Math.ceil(maxPagesToShow / 2) - 1;

      startPage = this.currentPage - maxPagesBeforeCurrent;
      endPage = this.currentPage + maxPagesAfterCurrent;

      if (startPage < 2) {
        endPage += 2 - startPage;
        startPage = 2;
      }

      if (endPage > this.totalPages - 1) {
        startPage -= endPage - (this.totalPages - 1);
        endPage = this.totalPages - 1;
      }

      if (startPage > 2) {
        pageNumbers.push(-1); // Add ellipsis before the start page
      }

      for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(i);
      }

      if (endPage < this.totalPages - 1) {
        pageNumbers.push(-1); // Add ellipsis after the end page
      }
    } else {
      for (let i = 2; i < this.totalPages; i++) {
        pageNumbers.push(i);
      }
    }

    return pageNumbers.sort((a,b) => a - b);
  }

  onSearch() {
    this.loadQuizzes();
  }

  onClearTitle() {
    this.searchOptions.title = '';
  }

  onSortDifficulty(direction:string, event:Event) {
    event.preventDefault();
    this.searchOptions.sort_difficulty = direction;
    this.loadQuizzes();
  }

  onSortCreated(direction: string, event: Event) {
    event.preventDefault();
    this.searchOptions.sort_created = direction;
    this.loadQuizzes();
  }

  getCategoryNameById(id: number) {
    return this.categories.find(c => c.id === id)?.name;
  }

  protected readonly API_BASEURL = API_BASEURL;
}
