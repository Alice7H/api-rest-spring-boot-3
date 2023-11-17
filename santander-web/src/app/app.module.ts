import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SideMenuComponent } from './shared/components/header/side-menu/side-menu.component';
import { FeatureComponent } from './user/feature/feature.component';
import { BalanceComponent } from './user/balance/balance.component';
import { CardComponent } from './user/card/card.component';
import { NewsComponent } from './user/news/news.component';
import { MenuItemComponent } from './shared/components/menu-item/menu-item.component';
import { AppMaterialModule } from './shared/app-material/app-material.module';
import { HeaderComponent } from './shared/components/header/header.component';
import { PanelComponent } from './shared/panel/panel.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        SideMenuComponent,
        FeatureComponent,
        MenuItemComponent,
        BalanceComponent,
        CardComponent,
        NewsComponent,
        PanelComponent
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        AppMaterialModule
    ]
})
export class AppModule { }
