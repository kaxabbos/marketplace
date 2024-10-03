import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ProductService} from "../product.service";
import {CategoryService} from "../../category/category.service";
import {NavigateDirective} from "../../navigate.directive";
import {AlertService} from "../../alert/alert.service";

@Component({
	selector: 'app-product-add',
	standalone: true,
	imports: [
		NgIf,
		ReactiveFormsModule,
		FormsModule,
		NavigateDirective
	],
	templateUrl: './product-add.component.html',
})

export class ProductAddComponent implements OnInit {
	productForm = new FormGroup({
		name: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		bind: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		category: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		count: new FormControl("", [Validators.required, Validators.min(1), Validators.maxLength(1000)]),
		price: new FormControl("", [Validators.required, Validators.min(0.01), Validators.maxLength(1000)]),
		description: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(5000)]),
	})

	categories: any[] = [];

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
		private productService: ProductService,
		private categoryService: CategoryService,
		private alert: AlertService,
	) {
	}

	ngOnInit(): void {
		this.authService.getUserProfile().add(() => {
			if (this.global.role !== 'SELLER') this.router.navigate(['/login']);
		});

		this.categoryService.categorySubject.subscribe(value => {
			this.categories = value.categories;
		})

		this.categoryService.findAll();
	}

	save() {
		this.productService.save(this.productForm.value).subscribe({
			next: ((res: any) => {
				this.router.navigate(['/product'], {queryParams: {id: res.data.id}});
			}),
			error: ((e: any) => {
				console.log(e.error)
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}
}
