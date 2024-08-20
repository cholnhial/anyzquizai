import { IQuestion } from './question.model';

export interface IQuizFull {
  id: number;
  title: string;
  numberOfQuestions: number,
  difficulty: string;
  categoryId: number;
  questions: IQuestion[],
  created: Date;
}
