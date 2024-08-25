import {Routes} from '@angular/router';
import {MainComponent} from "./main/main.component";
import {LoginComponent} from "./auth/login/login.component";
import {RegComponent} from "./auth/reg/reg.component";
import {UserComponent} from "./user/user.component";
import {ErrorComponent} from "./error/error.component";
import {StatsComponent} from "./stats/stats.component";
import {ProductComponent} from "./product/product.component";
import {ProductAddComponent} from "./product/product-add/product-add.component";

export const routes: Routes = [
	{path: "", component: MainComponent},

	{path: "reg", component: RegComponent},
	{path: "login", component: LoginComponent},

	{path: "users", component: UserComponent},

	{path: "products", component: ProductComponent},
	{path: "productAdd", component: ProductAddComponent},


	{path: "stats", component: StatsComponent},

	{path: "error", component: ErrorComponent},
	{path: "**", component: ErrorComponent},
];
