import { IAnswer } from './answer.model';

export interface IQuestion {
  question: string,
  correctAnswerLetter: string,
  answers: IAnswer[],
}
