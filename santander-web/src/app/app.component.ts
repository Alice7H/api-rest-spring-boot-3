import { Component } from '@angular/core';
import { UserService } from './user/services/user.service';
import { Observable, catchError, of } from 'rxjs';
import { User } from './user/model/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  users$: Observable<User[]> | null = null;
  title = 'santander-web';
  panelBalanceState = false;
  panelOpenState = false;
  panel2OpenState = false;

  constructor(private userService: UserService){
    this.refresh();
  }

  refresh() {
    this.users$ = this.userService.list();
    //.pipe(
      //catchError(error => {
        //console.log('Erro ao carregar usuários');
        //return of([]);
      //})
    //);
  }

  handleChangedPanelState(evento:boolean) {
    this.panelBalanceState = evento;
  }
}
