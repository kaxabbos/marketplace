import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {GlobalService} from "../global.service";
import {NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {ProductService} from "./product.service";
import {ProductCardComponent} from "./product-card/product-card.component";
import {FormsModule} from "@angular/forms";
import {CategoryService} from "../category/category.service";

@Component({
	selector: 'app-product',
	standalone: true,
	imports: [
		NgIf,
		ProductCardComponent,
		FormsModule
	],
	templateUrl: './product.component.html',
})
export class ProductComponent implements OnInit {

	filterName: string = '';
	filterCategory: string = '';

	products: any[] = [];
	categories: any[] = [];

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
		private productService: ProductService,
		private categoryService: CategoryService,
	) {
	}

	productsSorted() {
		let temp = this.products;

		temp = temp.sort((a, b) => a.id < b.id ? 1 : -1);

		temp = temp.filter(item => item.name.toLowerCase().includes(this.filterName.toLowerCase()));
		temp = temp.filter(item => item.category.toLowerCase().includes(this.filterCategory.toLowerCase()));

		return temp;
	}

	ngOnInit(): void {
		this.authService.getUserProfile();

		this.productService.productSubject.subscribe(value => {
			this.products = value.products;
		})
		this.categoryService.categorySubject.subscribe(value => {
			this.categories = value.categories;
		})

		this.productService.findAll();
		this.categoryService.findAll();
	}

	getRole() {
		return this.global.role;
	}

}
