import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Account } from '../model/account';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.scss']
})
export class BalanceComponent {
  @Input() panelOpenState: boolean = false;
  @Output() panelChange = new EventEmitter<boolean>();
  @Input() account:Account = {id: 0, number: "", agency: "", balance: 1324.64, limit: 1000 };
}
