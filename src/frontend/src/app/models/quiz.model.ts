export interface IQuiz {
  id: number;
  title: string;
  questions: number,
  uniqueCode: string;
  difficulty: string;
  categoryId: number;
  created: Date;
}
