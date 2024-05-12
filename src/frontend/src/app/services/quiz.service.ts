import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {API_BASEURL} from "../app.constants";
import {Observable} from "rxjs";
import {ICategory} from "../models/category.model";

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

}
