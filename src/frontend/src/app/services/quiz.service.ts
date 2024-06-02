import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {API_BASEURL} from "../app.constants";
import {Observable} from "rxjs";
import {ICategory} from "../models/category.model";
import {INewQuiz} from "../models/new-quiz.model";
import {IQuizFull} from "../models/quiz-full.model";

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http: HttpClient) { }

  search(options: any): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${API_BASEURL}/quiz`, {params: {...options}, observe: 'response'})
  }

  getAllCategories(): Observable<HttpResponse<ICategory[]>> {
    return this.http.get<ICategory[]>(`${API_BASEURL}/quiz/categories`, {observe: 'response'});
  }

  getQuizById(id: number): Observable<HttpResponse<IQuizFull>> {
    return this.http.get<IQuizFull>(`${API_BASEURL}/quiz/${id}`, {observe: 'response'});
  }

  create(quiz: INewQuiz): Observable<HttpResponse<IQuizFull>> {
    return this.http.post<IQuizFull>(`${API_BASEURL}/quiz`,quiz, {observe: 'response'});
  }

}
