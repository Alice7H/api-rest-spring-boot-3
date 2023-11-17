import { Account } from "./account";
import { Feature } from "./feature";
import { News } from "./news";

export interface User {
  id: number;
  name: string;
  account: Account;
  card: {
    id: number;
    number: string;
    limit: number;
  }
  features: Feature[];
  news: News[];
}
